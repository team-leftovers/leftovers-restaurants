package com.leftovers.restaurants.repository;

import com.leftovers.restaurants.model.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    Optional<Food> findFoodById(Integer id);
    List<Food> findAll();
    List<Food> findFoodByNameContainingIgnoreCase(String str);
    int deleteFoodById(Integer id);


    @Query(value = "select distinct tbl_food.id , tbl_food.name , tbl_food.restaurant_id , tbl_food.price , tbl_food.description from tbl_food left join tbl_food_tag on tbl_food_tag.food_id = tbl_food.id left join (select * from tbl_tag where name in (:tags)) as filt on filt.id = tag_id where tbl_food.name Like concat('%' , :str , '%')" , nativeQuery = true)
    List<Food> findFoodBySearchTermWithTags(@Param("str") String str , @Param("tags") List<String> tags);
}