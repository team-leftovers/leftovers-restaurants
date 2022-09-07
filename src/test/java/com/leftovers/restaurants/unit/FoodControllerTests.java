package com.leftovers.restaurants.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leftovers.restaurants.dto.CreateFoodDTO;
import com.leftovers.restaurants.dto.UpdateFoodDTO;
import com.leftovers.restaurants.model.Address;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.model.Restaurant;
import com.leftovers.restaurants.repository.AddressRepository;
import com.leftovers.restaurants.repository.FoodRepository;
import com.leftovers.restaurants.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import java.sql.Time;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FoodControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private FoodRepository repo;
    @Autowired
    private AddressRepository addrRepo;
    @Autowired
    private RestaurantRepository restRepo;

    private Address validAddress;
    private Restaurant validRestaurant;
    private Food validFood;
    private Food validUpdatedFood;
    private CreateFoodDTO validCreateDTO;
    private UpdateFoodDTO validUpdateDTO;
    private int restaurantId = 1;
    private final String endpoint = "/food";


    @BeforeAll
    void restaurantSetup() throws Exception {
        validAddress = Address.builder()
                .zipcode("44444")
                .country("USA")
                .state("CA")
                .city("Los Angeles")
                .streetAddress("1111 NE Some St.")
                .build();

        addrRepo.save(validAddress);

        validRestaurant = Restaurant.builder()
                .name("New Place")
                .phoneNo("5555555555")
                .website("somesite.webernets")
                .openTime(new Time(001100))
                .closeTime(new Time(120000))
                .addressId(1)
                .build();

        restRepo.save(validRestaurant);


        validFood = Food.builder()
                .id(1)
                .name("TEST NAME")
                .price(new BigDecimal(3.50))
                .description("This is a test case food")
                .restaurantId(restaurantId)
                .build();

        validUpdatedFood = Food.builder()
                .id(validFood.getId())
                .name("UPDATED TEST NAME")
                .price(validFood.getPrice())
                .description(validFood.getDescription())
                .restaurantId(validFood.getRestaurantId())
                .build();

        validCreateDTO = CreateFoodDTO.builder()
                .name(validFood.getName())
                .price(validFood.getPrice())
                .description(validFood.getDescription())
                .restaurantId(validFood.getRestaurantId())
                .build();

        validUpdateDTO = UpdateFoodDTO.builder()
                .name(validUpdatedFood.getName())
                .build();
    }

    @org.junit.jupiter.api.Test
    public void PostFoodTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(validFood.getName()));
    }

    @org.junit.jupiter.api.Test
    public void getAllFoodTest() throws Exception {
        repo.deleteAll();
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        repo.save(validFood);

        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @org.junit.jupiter.api.Test
    public void getFoodByIdTest() throws Exception {
        var result = repo.save(validFood);

        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint + "/" + result.getId())
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @org.junit.jupiter.api.Test
    public void putFoodTest() throws Exception {
        var result = repo.save(validFood);

        mvc.perform(MockMvcRequestBuilders
                        .put(endpoint + "/" + result.getId())
                        .content(asJsonString(validUpdateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(validUpdatedFood.getName()));
    }

    @org.junit.jupiter.api.Test
    public void deleteFoodTest() throws Exception {
        var result = repo.save(validFood);

        mvc.perform(MockMvcRequestBuilders
                    .delete(endpoint + "/" + result.getId()))
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
