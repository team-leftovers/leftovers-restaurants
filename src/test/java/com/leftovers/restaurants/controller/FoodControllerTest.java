package com.leftovers.restaurants.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;



@SpringBootTest
@AutoConfigureMockMvc
class FoodControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("test for return all food")
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/food")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }


    @Test
    @DisplayName("test for get foodBy id")
    void getFoodById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/food/by-id/{id}" , 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
//    @Test
//    void getFoodByIdShouldReturnEmpty() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/food/by-id/{id}" , 0)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[*]").isEmpty());
//    }

    @Test
    @DisplayName("test for get food by name")
    void getFoodByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/food/{name}" , "shorma")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").value("shorma"));
    }

    @Test
    @DisplayName("test for updating food name")
    void updateFoodName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/food/update-name/{id}" , 1)
                        .param("name" , "shorma"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Updated Successfully"));
    }

    @Test
    @DisplayName("test for upating price")
    void updateFoodPricce() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/food/update-price/{id}" , 1)
                        .param("price" , "16.00"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Updated Successfully"));
    }

    @Test
    @DisplayName("test for updating description")
    void updateFoodDescription() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/food/update-description/{id}" , 1)
                        .param("description" , "new description from tests"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Updated Successfully"));
    }

    @Test
    @DisplayName("test for updating the restaurant of food")
    void updateFoodRestaurant() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/food/update-restaurant/{id}" , 1)
                        .param("restaurantId" , "2"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Updated Successfully"));
    }

    @Test
    @DisplayName("test to delete element by id")
    void deleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/food/delete").param("id" , "1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Entry delted Successfully"));
    }
}