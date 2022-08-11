package com.leftovers.restaurants;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leftovers.restaurants.dao.RestaurantDao;
import com.leftovers.restaurants.model.Restaurant;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Time;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class LeftoversRestaurantsApplicationTests {
    String endpoint = "/restaurants";

    @Autowired
    RestaurantDao dao;

    @Autowired
    private MockMvc mock;
    @Test
    @Order(1)
    @DisplayName("Restaurant Post Test - 201")
    void restaurantPostTest201() throws Exception {
        var restaurant = new Restaurant("Billy",
                1,
                "(503) 111-1111",
                new Time (100000),
                new Time (180000));

        mock.perform(MockMvcRequestBuilders
                .post(endpoint)
                .content(asJsonString(restaurant))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(restaurant.getName()));
    }

    @Test
    @Order(2)
    @DisplayName("Restaurant Post Test - 406")
    void RestaurantPostTest406() throws Exception {
        var restaurant = new Restaurant("Failure",
                1,
                "(503) 111-1111",
                new Time (100000),
                new Time (180000));
        restaurant.setId(1);

        mock.perform(MockMvcRequestBuilders
                .post(endpoint)
                .content(asJsonString(restaurant))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    @Order(3)
    @DisplayName("Restaurant Delete Test")
    void RestaurantDeleteTest() throws Exception {
        var result = dao.getLatestRestaurant();

        mock.perform(MockMvcRequestBuilders
                .delete(endpoint + "/{id}", result.getId().toString()))
                .andExpect(status().isOk());

        mock.perform(MockMvcRequestBuilders
                .delete(endpoint + "/{id}", result.getId().toString()))
                .andExpect(status().isGone());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}