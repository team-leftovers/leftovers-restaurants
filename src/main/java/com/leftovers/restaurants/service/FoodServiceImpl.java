package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.CreateFoodDTO;
import com.leftovers.restaurants.dto.FullFoodDTO;
import com.leftovers.restaurants.dto.ShortFoodDTO;
import com.leftovers.restaurants.dto.UpdateFoodDTO;
import com.leftovers.restaurants.exception.NoSuchFoodException;
import com.leftovers.restaurants.exception.NoSuchRestaurantException;
import com.leftovers.restaurants.mapper.FoodMapper;
import com.leftovers.restaurants.repository.FoodRepository;
import com.leftovers.restaurants.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepo;
    private final RestaurantRepository restRepo;

    @Transactional
    @Override
    public FullFoodDTO createNewFood(CreateFoodDTO dto) {
        try {
            var food = foodRepo.save(FoodMapper.toFood(dto));
            return FoodMapper.toFullDTO(food);
        }

        catch(Exception e) {
            log.error(e.getMessage());
            throw new NoSuchRestaurantException(dto.restaurantId);
        }
    }

    @Override
    public List<ShortFoodDTO> getAllFood() {
        return foodRepo.findAll().stream()
                .map(item -> FoodMapper.toShortDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public FullFoodDTO getFood(Integer id) {
        var food = foodRepo.findFoodById(id)
                .orElseThrow(() -> new NoSuchFoodException(id));

        return FoodMapper.toFullDTO(food);
    }

    @Transactional
    @Override
    public FullFoodDTO updateFood(Integer id, UpdateFoodDTO dto) {
        var food = foodRepo.findFoodById(id)
                .orElseThrow(() -> new NoSuchFoodException(id));

        ifNotNull(dto.name, food::setName);
        ifNotNull(dto.description, food::setDescription);
        ifNotNull(dto.price, food::setPrice);

        return FoodMapper.toFullDTO(foodRepo.save(food));
    }

    @Transactional
    @Override
    public void deleteFood(Integer id) {
        if(foodRepo.deleteFoodById(id) == 0)
            throw new NoSuchFoodException(id);
    }


    // Utility function to execute function if value not null
    private <T> void ifNotNull(T val, Consumer<T> func) {
        if(val != null)
            func.accept(val);
    }
}
