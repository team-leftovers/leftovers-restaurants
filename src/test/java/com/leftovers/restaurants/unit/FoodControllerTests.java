package com.leftovers.restaurants.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leftovers.restaurants.dto.CreateFoodDTO;
import com.leftovers.restaurants.dto.UpdateFoodDTO;
import com.leftovers.restaurants.dto.UpdateTagsDTO;
import com.leftovers.restaurants.model.Address;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.model.Restaurant;
import com.leftovers.restaurants.model.Tag;
import com.leftovers.restaurants.repository.AddressRepository;
import com.leftovers.restaurants.repository.FoodRepository;
import com.leftovers.restaurants.repository.RestaurantRepository;
import com.leftovers.restaurants.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.HashSet;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FoodControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private FoodRepository foodRepo;
    @Autowired
    private AddressRepository addrRepo;
    @Autowired
    private RestaurantRepository restRepo;
    @Autowired
    private TagRepository tagRepo;


    // ======================================= //
    // ============== VARIABLES ============== //
    // ======================================= //

    private Restaurant  restaurant;
    private Address     address;
    private Food        food;
    private Tag         tag;

    private final String endpoint = "/food";


    // ======================================= //
    // ================ SETUP ================ //
    // ======================================= //

    @BeforeEach
    void restaurantSetup() throws Exception {
        address = Address.builder()
                .zipcode("44444")
                .country("USA")
                .state("CA")
                .city("Los Angeles")
                .streetAddress("1111 NE Some St.")
                .build();
        address = addrRepo.save(address);

        restaurant = Restaurant.builder()
                .name("New Place")
                .phoneNo("5555555555")
                .website("somesite.webernets")
                .openTime(new Time(001100))
                .closeTime(new Time(120000))
                .addressId(address.getId())
                .build();
        restaurant = restRepo.save(restaurant);

        food = Food.builder()
                .name("TEST NAME")
                .price(new BigDecimal(3.50))
                .description("This is a test case food")
                .restaurantId(restaurant.getId())
                .build();
        foodRepo.save(food);

        tag = Tag.builder()
                .name("RESTARAUNT TAG")
                .build();
        tag = tagRepo.save(tag);
    }

    @AfterEach
    void teardown() {
        restRepo.deleteAll();
        addrRepo.deleteAll();
        foodRepo.deleteAll();
        tagRepo.deleteAll();

        restaurant = null;
        address = null;
        food = null;
        tag = null;
    }


    // ======================================= //
    // ================ TESTS ================ //
    // ======================================= //
    @org.junit.jupiter.api.Test
    public void PostFoodTest() throws Exception {
        var dto = CreateFoodDTO.builder()
            .name(food.getName())
            .price(food.getPrice())
            .description(food.getDescription())
            .restaurantId(food.getRestaurantId())
        .build();

        mvc.perform(MockMvcRequestBuilders
                        .post(endpoint)
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(food.getName()));
    }

    @org.junit.jupiter.api.Test
    public void getAllFoodTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        foodRepo.deleteAll();

        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @org.junit.jupiter.api.Test
    public void getFoodByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint + "/" + food.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @org.junit.jupiter.api.Test
    public void putFoodTest() throws Exception {
        var dto = UpdateFoodDTO.builder()
                .name("New Food Name")
            .build();

        mvc.perform(MockMvcRequestBuilders
                        .put(endpoint + "/" + food.getId())
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(dto.name));
    }

    @org.junit.jupiter.api.Test
    public void deleteFoodTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                    .delete(endpoint + "/" + food.getId()))
                .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders
                        .delete(endpoint + "/" + food.getId()))
                .andExpect(status().isNotFound());
    }

    @org.junit.jupiter.api.Test
    public void putFoodTagsTest() throws Exception {
        var dto = UpdateTagsDTO.builder()
                .id(tag.getId())
            .build();

        mvc.perform(MockMvcRequestBuilders
                        .put(endpoint + "/" + food.getId() + "/" + "tags")
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags[0].name").value(tag.getName()));
    }

    @org.junit.jupiter.api.Test
    public void deleteFoodTagsTest() throws Exception {
        food.setFoodTags(new HashSet<Tag>());
        food.getFoodTags().add(tag);
        foodRepo.save(food);

        mvc.perform(MockMvcRequestBuilders
                        .delete(endpoint + "/" + food.getId() + "/tags/" + tag.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                        .delete(endpoint + "/" + food.getId() + "/tags/" + tag.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    // ======================================= //
    // ========== HELPER FUNCTIONS =========== //
    // ======================================= //

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
