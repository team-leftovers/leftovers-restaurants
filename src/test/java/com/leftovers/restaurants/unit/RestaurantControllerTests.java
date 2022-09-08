package com.leftovers.restaurants.unit;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.leftovers.restaurants.dto.CreateRestaurantDTO;
import com.leftovers.restaurants.dto.UpdateAddressDTO;
import com.leftovers.restaurants.dto.UpdateRestaurantDTO;
import com.leftovers.restaurants.dto.UpdateTagsDTO;
import com.leftovers.restaurants.model.Address;
import com.leftovers.restaurants.model.Restaurant;
import com.leftovers.restaurants.model.Tag;
import com.leftovers.restaurants.repository.AddressRepository;
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

import java.sql.Time;
import java.util.HashSet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private RestaurantRepository restRepo;
    @Autowired
    private AddressRepository addrRepo;
    @Autowired
    private TagRepository tagRepo;

    // ======================================= //
    // ============== VARIABLES ============== //
    // ======================================= //

    private Restaurant  restaurant;
    private Address     address;
    private Tag         tag;

    private final String endpoint = "/restaurants";


    // ======================================= //
    // ================ SETUP ================ //
    // ======================================= //

    @BeforeEach
    void setup() {
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

        tag = Tag.builder()
                .name("RESTARAUNT TAG")
                .build();
        tag = tagRepo.save(tag);
    }

    @AfterEach
    void teardown() {
        restRepo.deleteAll();
        addrRepo.deleteAll();
        tagRepo.deleteAll();

        restaurant = null;
        address = null;
        tag = null;
    }

    // ======================================= //
    // ================ TESTS ================ //
    // ======================================= //

    @org.junit.jupiter.api.Test
    public void PostRestaurantTest() throws Exception {
        var dto = CreateRestaurantDTO.builder()
                .name(restaurant.getName())
                .zipcode(address.getZipcode())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .streetAddress(address.getStreetAddress())
                .phoneNo(restaurant.getPhoneNo())
                .website(restaurant.getWebsite())
                .openTime(restaurant.getOpenTime())
                .closeTime(restaurant.getCloseTime())
                .build();

        mvc.perform(MockMvcRequestBuilders
                        .post(endpoint)
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(restaurant.getName()));
    }

    @org.junit.jupiter.api.Test
    public void getAllRestaurantTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @org.junit.jupiter.api.Test
    public void getRestaurantByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint + "/" + restaurant.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @org.junit.jupiter.api.Test
    public void putRestaurantTest() throws Exception {
        var dto = UpdateRestaurantDTO.builder()
                .name("New Restaurant")
                .website("newsite.interwebs")
                .build();

        mvc.perform(MockMvcRequestBuilders
                        .put(endpoint + "/" + restaurant.getId())
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(dto.name));
    }

    @org.junit.jupiter.api.Test
    public void deleteRestaurantTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete(endpoint + "/" + restaurant.getId()))
                .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders
                        .delete(endpoint + "/" + restaurant.getId()))
                .andExpect(status().isNotFound());
    }

    @org.junit.jupiter.api.Test
    public void putAddressTest() throws Exception {
        var dto = UpdateAddressDTO.builder()
                .id(restaurant.getAddressId())
                .state("NY")
                .city("New York")
                .build();

        mvc.perform(MockMvcRequestBuilders
                        .put(endpoint + "/" + restaurant.getId() + "/" + "address")
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value(dto.state));
    }

    @org.junit.jupiter.api.Test
    public void putRestaurantTagsTest() throws Exception {
        var dto = UpdateTagsDTO.builder()
                .id(tag.getId())
                .build();

        mvc.perform(MockMvcRequestBuilders
                        .put(endpoint + "/" + restaurant.getId() + "/" + "tags")
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags[0].name").value(tag.getName()));
    }

    @org.junit.jupiter.api.Test
    public void deleteRestaurantTagsTest() throws Exception {
        restaurant.setRestaurantTags(new HashSet<Tag>());
        restaurant.getRestaurantTags().add(tag);
        restRepo.save(restaurant);

        mvc.perform(MockMvcRequestBuilders
                        .delete(endpoint + "/" + restaurant.getId() + "/tags/" + tag.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                        .delete(endpoint + "/" + restaurant.getId() + "/tags/" + tag.getId())
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
