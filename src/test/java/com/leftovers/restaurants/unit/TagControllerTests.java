package com.leftovers.restaurants.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leftovers.restaurants.DeleteResultHandler;
import com.leftovers.restaurants.dto.CreateFoodDTO;
import com.leftovers.restaurants.dto.CreateTagDTO;
import com.leftovers.restaurants.dto.UpdateFoodDTO;
import com.leftovers.restaurants.dto.UpdateTagDTO;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.model.Tag;
import com.leftovers.restaurants.repository.FoodRepository;
import com.leftovers.restaurants.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private TagRepository repo;

    private Tag validTag;
    private Tag validUpdateTag;
    private CreateTagDTO validCreateDTO;
    private UpdateTagDTO validUpdateDTO;
    private final String endpoint = "/tags";

    @BeforeEach
    void setUp() {
        validTag = Tag.builder()
                .id(1)
                .name("TEST TAG")
            .build();

        validUpdateTag = Tag.builder()
                .id(validTag.getId())
                .name("UPDATED TEST TAG")
            .build();

        validCreateDTO = CreateTagDTO.builder()
                .name(validTag.getName())
            .build();

        validUpdateDTO = UpdateTagDTO.builder()
                .name(validUpdateTag.getName())
            .build();
    }

    @AfterEach
    void tearDown() {

    }

    @org.junit.jupiter.api.Test
    public void PostFoodTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(validTag.getName()))
                .andDo(DeleteResultHandler.deleteResult(repo));
    }

    @org.junit.jupiter.api.Test
    public void getAllFoodTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @org.junit.jupiter.api.Test
    public void getFoodByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint + "/1")
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @org.junit.jupiter.api.Test
    public void putFoodTest() throws Exception {
        var result = mvc.perform(MockMvcRequestBuilders
                        .post(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        mvc.perform(MockMvcRequestBuilders
                        .put(endpoint + "/" + getIdFromResult(result))
                        .content(asJsonString(validUpdateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(validUpdateTag.getName()));

        mvc.perform(MockMvcRequestBuilders
                        .delete(endpoint + "/" + getIdFromResult(result)))
                .andExpect(status().isNoContent());
    }

    @org.junit.jupiter.api.Test
    public void deleteFoodTest() throws Exception {
        var result = mvc.perform(MockMvcRequestBuilders
                        .post(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        mvc.perform(MockMvcRequestBuilders
                        .delete(endpoint + "/" + getIdFromResult(result)))
                .andExpect(status().isNoContent());
    }

    // Helper functions
    private Integer getIdFromResult(MvcResult result) {
        String location = result.getResponse().getHeader("Location");
        return Integer.parseInt(location.substring(location.lastIndexOf("/") + 1));
    }
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
