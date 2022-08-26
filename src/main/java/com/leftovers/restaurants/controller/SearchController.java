package com.leftovers.restaurants.controller;

import com.leftovers.restaurants.dto.GetRestaurantDto;
import com.leftovers.restaurants.model.Restaurant;
import com.leftovers.restaurants.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(path = "/search")
@RequiredArgsConstructor
public class SearchController {

    private final String Mapping = "search";
    private final SearchService service;

    @RequestMapping(path = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Set<GetRestaurantDto>> getRestaurantByFood(@RequestParam String foodName , @RequestBody String tagsFromFront) {
        log.info("Searching for Restaurants by foodName" + foodName + "  tags   " + tagsFromFront);
        List<String> tags = Arrays.asList(tagsFromFront.split(","));
        return ResponseEntity.of(Optional.ofNullable(service.getRestaurantsByFood(foodName , tags)));
    }
}
