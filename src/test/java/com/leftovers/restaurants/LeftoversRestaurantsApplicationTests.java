package com.leftovers.restaurants;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leftovers.restaurants.model.Restaurant;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
@AutoConfigureMockMvc
class LeftoversRestaurantsApplicationTests {
    @Autowired
    private MockMvc mock;
    @Test
    @DisplayName("Restaurant Post Test - Success")
    void restaurantPostTestSuccess() throws Exception {
        var restaurant = new Restaurant("Jerry",
                1,
                "(503) 111-1111",
                new Time (100000),
                new Time (180000));

        mock.perform(MockMvcRequestBuilders
                .post("/restaurant")
                .content(asJsonString(restaurant))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(restaurant.getName()));
    }

    @Test
    @DisplayName("Restaurant Post Test - Fail")
    void RestaurantPostTestFail() throws Exception {
        var restaurant = new Restaurant("Failure",
                1,
                "(503) 111-1111",
                new Time (100000),
                new Time (180000));
        restaurant.setId(1);

        mock.perform(MockMvcRequestBuilders
                .post("/restaurant")
                .content(asJsonString(restaurant))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());
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
