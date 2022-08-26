package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.*;
import com.leftovers.restaurants.exception.NoSuchAddressException;
import com.leftovers.restaurants.exception.NoSuchRestaurantException;
import com.leftovers.restaurants.mapper.AddressMapper;
import com.leftovers.restaurants.mapper.RestaurantMapper;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.repository.AddressRepository;
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
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restRepo;
    private final AddressRepository addrRepo;

    @Transactional
    @Override
    public FullRestaurantDTO createNewRestaurant(CreateRestaurantDTO dto) {
        var address = AddressMapper.toAddress(dto);
        log.info(address.toString());
        address = addrRepo.save(address);

        var restaurant = RestaurantMapper.toRestaurant(dto);
        restaurant.setAddressId(address.getId());
        restaurant.setAddress(address);
        restaurant = restRepo.save(restaurant);

        return RestaurantMapper.toFullDTO(restaurant);
    }

    @Override
    public List<ShortRestaurantDTO> getAllRestaurants() {
        return restRepo.findAll().stream()
                .map(item -> RestaurantMapper.toShortDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public FullRestaurantDTO getRestaurant(Integer id) {
        var restaurant = restRepo.findRestaurantById(id)
                .orElseThrow(() -> new NoSuchRestaurantException(id));
        return RestaurantMapper.toFullDTO(restaurant);
    }

    @Transactional
    @Override
    public FullRestaurantDTO updateRestaurant(Integer id, UpdateRestaurantDTO dto) {
        var restaurant = restRepo.findRestaurantById(id)
                .orElseThrow(() -> new NoSuchRestaurantException(id));

        ifNotNull(dto.name, restaurant::setName);
        ifNotNull(dto.phoneNo, restaurant::setPhoneNo);
        ifNotNull(dto.website, restaurant::setWebsite);
        ifNotNull(dto.openTime, restaurant::setOpenTime);
        ifNotNull(dto.closeTime, restaurant::setCloseTime);

        return RestaurantMapper.toFullDTO(restRepo.save(restaurant));
    }

    @Transactional
    @Override
    public void deleteRestaurant(Integer id) {
        if(restRepo.deleteRestaurantById(id) == 0)
            throw new NoSuchRestaurantException(id);
    }

    @Override
    public List<Food> getAllFoodByRestaurant(Integer id) {
        notNull(id);
        var restaurant = restRepo.findRestaurantById(id);
        if(restaurant.isEmpty())
            throw new NoSuchRestaurantException(id);
        return restaurant.get().getMenuItems();
    }

    @Transactional
    @Override
    public AddressDTO updateRestaurantAddress(UpdateAddressDTO dto) {
        var address = addrRepo.findAddressById(dto.id)
                .orElseThrow(() -> new NoSuchAddressException(dto.id));

        ifNotNull(dto.zipcode, address::setZipcode);
        ifNotNull(dto.city, address::setCity);
        ifNotNull(dto.state, address::setState);
        ifNotNull(dto.country, address::setCountry);
        ifNotNull(dto.streetAddress, address::setStreetAddress);
        ifNotNull(dto.unitNumber, address::setUnitNumber);

        return AddressMapper.toDTO(addrRepo.save(address));
    }


    // Utility function to execute function if value not null
    private <T> void ifNotNull(T val, Consumer<T> func) {
        if(val != null)
            func.accept(val);
    }

    // Utility function to determine if input was incorrectly null
    private void notNull(Object... ids) {
        for(var id: ids) {
            if (id == null)
                throw new IllegalArgumentException("Expected value but received null.");
        }
    }
}
