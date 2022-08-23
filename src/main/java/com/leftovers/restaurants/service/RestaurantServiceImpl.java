package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.CreateRestaurantDto;
import com.leftovers.restaurants.dto.UpdateRestaurantDto;
import com.leftovers.restaurants.exception.NoSuchRestaurantException;
import com.leftovers.restaurants.model.Address;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.model.Restaurant;
import com.leftovers.restaurants.repository.AddressRepository;
import com.leftovers.restaurants.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restRepo;
    private final AddressRepository addrRepo;

    @Transactional
    @Override
    public Restaurant createNewRestaurant(CreateRestaurantDto dto) {
        notNull(dto);
        var address = Address.builder()
                .zipcode(dto.zipcode)
                .state(dto.state)
                .city(dto.city)
                //.country(dto.country)
                .streetAddress(dto.streetAddress)
                .unitNumber(dto.unitNumber)
            .build();

        address = addrRepo.save(address);

        var restaurant = Restaurant.builder()
                .name(dto.name)
                .phoneNo(dto.phoneNo)
                .website(dto.website)
                .openTime(dto.openTime)
                .closeTime(dto.closeTime)
                .address(address) //address.id
            .build();

        return restRepo.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restRepo.findAll();
    }

    @Override
    public Restaurant getRestaurant(Integer id) {
        notNull(id);
        return restRepo.findRestaurantById(id)
                .orElseThrow(() -> new NoSuchRestaurantException(id));
    }

    @Transactional
    @Override
    public Restaurant updateRestaurant(Integer id, UpdateRestaurantDto dto) {
        notNull(id, dto);
        var result = restRepo.findRestaurantById(id);
        if(result.isEmpty())
            throw new NoSuchRestaurantException(id);

        Restaurant restaurant = result.get();

        restaurant.getAddress().setZipcode(dto.zipcode);
        restaurant.getAddress().setState(dto.state);
        restaurant.getAddress().setCity(dto.city);
        restaurant.getAddress().setStreetAddress(dto.streetAddress);
        restaurant.getAddress().setUnitNumber(dto.unitNumber);

        addrRepo.save(restaurant.getAddress());

        restaurant.setName(dto.name);
        restaurant.setPhoneNo(dto.phoneNo);
        restaurant.setWebsite(dto.website);
        restaurant.setOpenTime(dto.openTime);
        restaurant.setCloseTime(dto.closeTime);

        return restRepo.save(restaurant);
    }

    @Transactional
    @Override
    public void deleteRestaurant(Integer id) {
        notNull(id);
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

    // Utility function to determine if input was incorrectly null
    private void notNull(Object... ids) {
        for(var id: ids) {
            if(id == null)
                throw new IllegalArgumentException("Expected value but received null.");
        }
    }

    //private Object checkRepo(CrudRepo repo, int id)
        // checks if object exists and throws if not
}
