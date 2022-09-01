//package com.leftovers.restaurants.controller;
//
//import com.leftovers.restaurants.model.Food;
//import com.leftovers.restaurants.model.Restaurant;
//import com.leftovers.restaurants.repository.FoodRepository;
//import com.leftovers.restaurants.repository.RestaurantRepository;
//import lombok.RequiredArgsConstructor;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.Before;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.math.BigDecimal;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@RequiredArgsConstructor
//class SearchControllerTest  {
//    @Autowired
//    private MockMvc mockMvc;
//    @Before
//    void setup() throws Exception {
//        JSONObject testData = new JSONObject();
//        testData.put("name" , new String("testFood"));
//        testData.put("description" , "test food description");
//        testData.put("price" , 120);
//        testData.put("restaurantId" , 1);
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/food")
//                .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(testData))
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print());
//
//    }
//    void tearDown() {
//    }
//
//    @Test
//    @DisplayName("when the food is present searching for a specific food")
//    void searchingForFood() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/search")
//                        .param("foodName" , "testFood")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").value(1));
//    }
//
//    @Test
//    @DisplayName("when nothing is found")
//    void searchShouldReturnEmptyList() throws Exception {
//        setup();
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/search")
//                        .param("foodName" , "dmystringforemptyreturncheck")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isEmpty());
//    }
//
//    @Test
//    @DisplayName("When Searching for Empty string")
//    void searchShouldReturnAllElements() throws Exception {
//        setup();
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/search")
//                        .param("foodName" , "")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
//    }
//}