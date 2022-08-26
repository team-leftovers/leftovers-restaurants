package com.leftovers.restaurants.repository;

import com.leftovers.restaurants.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findRestaurantById(Integer id);
    Optional<Restaurant> findTopByOrderByIdDesc();
    List<Restaurant> findRestaurantByNameContainingIgnoreCase(String str);
    List<Restaurant> findAll();
    int deleteRestaurantById(Integer id);

    @Query(value = "select distinct tbl_restaurant.id , tbl_restaurant.name , tbl_restaurant.address_id , tbl_restaurant.phone_no" +
            " , tbl_restaurant.website , tbl_restaurant.open_time , tbl_restaurant.close_time , tbl_restaurant.rating , tbl_restaurant.rating_count from tbl_restaurant left join tbl_restaurant_tag on tbl_restaurant_tag.restaurant_id = tbl_restaurant.id " +
            "left join (select * from tbl_tag where name in (:tags)) as filt on filt.id = tag_id where tbl_restaurant.name Like concat('%' , :str , '%')" , nativeQuery = true)
    List<Restaurant> findRestaurantBySearchTermWithTags(@Param("str") String str , @Param("tags") List<String> tags);
}