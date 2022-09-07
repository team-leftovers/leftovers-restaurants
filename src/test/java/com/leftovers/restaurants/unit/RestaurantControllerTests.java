package com.leftovers.restaurants.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leftovers.restaurants.dto.CreateRestaurantDTO;
import com.leftovers.restaurants.dto.UpdateAddressDTO;
import com.leftovers.restaurants.dto.UpdateRestaurantDTO;
import com.leftovers.restaurants.model.Address;
import com.leftovers.restaurants.model.Restaurant;
import com.leftovers.restaurants.repository.AddressRepository;
import com.leftovers.restaurants.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Time;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestaurantControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private RestaurantRepository repo;
    @Autowired
    private AddressRepository addrRepo;

    private Restaurant  validRestaurant;
    private Restaurant  validUpdatedRestaurant;
    private Address     validAddress;

    private CreateRestaurantDTO validCreateDTO;
    private UpdateRestaurantDTO validUpdateRestaurantDTO;
    private UpdateAddressDTO    validUpdateAddresDTO;
    private int addressId;
    private Restaurant result;
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

        addressId = addrRepo.save(validAddress).getId();

        validRestaurant = Restaurant.builder()
                .name("New Place")
                .phoneNo("5555555555")
                .website("somesite.webernets")
                .openTime(new Time(001100))
                .closeTime(new Time(120000))
                .addressId(addressId)
            .build();

        result = repo.save(validRestaurant);

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
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(validRestaurant.getName()));
    }

    @org.junit.jupiter.api.Test
    public void getAllRestaurantTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        repo.deleteAll();
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint)
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @org.junit.jupiter.api.Test
    public void getRestaurantByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(endpoint + "/" + result.getId())
                        .content(asJsonString(validCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @org.junit.jupiter.api.Test
    public void putRestaurantTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put(endpoint + "/" + result.getId())
                        .content(asJsonString(validUpdateRestaurantDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(validUpdatedRestaurant.getName()));
    }

    @org.junit.jupiter.api.Test
    public void deleteRestaurantTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete(endpoint + "/" + result.getId()))
                .andExpect(status().isNoContent());
    }

    @org.junit.jupiter.api.Test
    public void putAddressTest() throws Exception {
        validUpdateAddresDTO = UpdateAddressDTO.builder()
                .id(result.getAddressId())
                .state("NY")
                .city("New York")
            .build();

        mvc.perform(MockMvcRequestBuilders
                        .put(endpoint + "/" + result.getId() + "/" + "address")
                        .content(asJsonString(validUpdateAddresDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value(validUpdateAddresDTO.state));
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
