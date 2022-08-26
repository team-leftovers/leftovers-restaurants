package com.leftovers.restaurants.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leftovers.restaurants.DeleteResultHandler;
import com.leftovers.restaurants.controller.RestaurantController;
import com.leftovers.restaurants.dto.CreateRestaurantDTO;
import com.leftovers.restaurants.dto.UpdateAddressDTO;
import com.leftovers.restaurants.dto.UpdateFoodDTO;
import com.leftovers.restaurants.dto.UpdateRestaurantDTO;
import com.leftovers.restaurants.model.Address;
import com.leftovers.restaurants.model.Restaurant;
import com.leftovers.restaurants.repository.AddressRepository;
import com.leftovers.restaurants.repository.FoodRepository;
import com.leftovers.restaurants.repository.RestaurantRepository;
import com.leftovers.restaurants.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Time;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private RestaurantRepository repo;

    private Restaurant  validRestaurant;
    private Restaurant  validUpdatedRestaurant;
    private Address     validAddress;

    private CreateRestaurantDTO validCreateDTO;
    private UpdateRestaurantDTO validUpdateRestaurantDTO;
    private UpdateAddressDTO    validUpdateAddresDTO;
    private final String endpoint = "/restaurants";

    @BeforeEach
    void setUp() {
        validAddress = Address.builder()
                    .zipcode("44444")
                    .country("USA")
                    .state("CA")
                    .city("Los Angeles")
                    .streetAddress("1111 NE Some St.")
                .build();

        validRestaurant = Restaurant.builder()
                .name("New Place")
                .phoneNo("5555555555")
                .website("somesite.webernets")
                .openTime(new Time(001100))
                .closeTime(new Time(120000))
                .addressId(1)
            .build();

        validCreateDTO = CreateRestaurantDTO.builder()
                .name(validRestaurant.getName())
                .zipcode(validAddress.getZipcode())
                .city(validAddress.getCity())
                .state(validAddress.getState())
                .country(validAddress.getCountry())
                .streetAddress(validAddress.getStreetAddress())
                .phoneNo(validRestaurant.getPhoneNo())
                .website(validRestaurant.getWebsite())
                .openTime(validRestaurant.getOpenTime())
                .closeTime(validRestaurant.getCloseTime())
            .build();

        validUpdatedRestaurant = Restaurant.builder()
                .name("that new place")
                .phoneNo(validRestaurant.getPhoneNo())
                .website("newsite.interwebs")
                .openTime(validRestaurant.getOpenTime())
                .closeTime(validRestaurant.getCloseTime())
                .addressId(validRestaurant.getAddressId())
            .build();

        validUpdateRestaurantDTO = UpdateRestaurantDTO.builder()
                .name(validUpdatedRestaurant.getName())
                .website(validUpdatedRestaurant.getWebsite())
            .build();
    }

    @AfterEach
    void tearDown() {

    }

    @org.junit.jupiter.api.Test
    public void PostRestaurantTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(validRestaurant.getName()))
                .andDo(DeleteResultHandler.deleteResult(repo));
    }

    @org.junit.jupiter.api.Test
    public void getAllRestaurantTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @org.junit.jupiter.api.Test
    public void getRestaurantByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint + "/1")
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @org.junit.jupiter.api.Test
    public void putRestaurantTest() throws Exception {
        var result = mvc.perform(MockMvcRequestBuilders
                        .post(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        mvc.perform(MockMvcRequestBuilders
                        .put(endpoint + "/" + getIdFromResult(result))
                        .content(asJsonString(validUpdateRestaurantDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(validUpdatedRestaurant.getName()));

        mvc.perform(MockMvcRequestBuilders
                        .delete(endpoint + "/" + getIdFromResult(result)))
                .andExpect(status().isNoContent());
    }

    @org.junit.jupiter.api.Test
    public void deleteRestaurantTest() throws Exception {
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

    @org.junit.jupiter.api.Test
    public void putAddressTest() throws Exception {
        var result = mvc.perform(MockMvcRequestBuilders
                        .post(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        var restaurant = repo.findRestaurantById(getIdFromResult(result)).get();
        validUpdateAddresDTO = UpdateAddressDTO.builder()
                .id(restaurant.getAddressId())
                .state("NY")
                .city("New York")
            .build();
        log.error(validUpdateAddresDTO.id.toString());

        mvc.perform(MockMvcRequestBuilders
                        .put(endpoint + "/" + restaurant.getId() + "/" + "address")
                        .content(asJsonString(validUpdateAddresDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value(validUpdateAddresDTO.state));

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
