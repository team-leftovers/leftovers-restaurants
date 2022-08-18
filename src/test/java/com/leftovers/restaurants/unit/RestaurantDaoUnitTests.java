package com.leftovers.restaurants.unit;

import com.leftovers.restaurants.controller.RestaurantController;
import com.leftovers.restaurants.model.Restaurant;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;

@SpringBootTest
//@DataJpaTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestaurantDaoUnitTests {
    @Autowired
    RestaurantDao restaurants;
    @Autowired
    RestaurantController restaurantController;

//    @BeforeAll
//    static void setup() {
//
//    }

//    @AfterAll
//    static void teardown() {
//
//    }

    @Test
    @Order(1)
    public void restaurantValidationTest() {
        var result = restaurants.getLatestRestaurant();
        assertFalse(restaurantController.restaurantInputValidation(result));

        var r1 = new Restaurant();
        assertFalse(restaurantController.restaurantInputValidation(r1));

        r1.setOpenTime(new Time(010000));
        assertFalse(restaurantController.restaurantInputValidation(r1));

        r1.setCloseTime(new Time(200000));
        assertFalse(restaurantController.restaurantInputValidation(r1));

        r1.setPhoneNo("phone number");
        assertFalse(restaurantController.restaurantInputValidation(r1));

        r1.setAddressId(result.getAddressId());
        assertFalse(restaurantController.restaurantInputValidation(r1));

        r1.setName("name");
        assertTrue(restaurantController.restaurantInputValidation(r1));
    }
}