package com.leftovers.restaurants.controller;

import com.leftovers.restaurants.dto.*;
import com.leftovers.restaurants.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/food")
@RequiredArgsConstructor
public class FoodController {
    private static final String MAPPING = "/food";
    private final FoodService service;


    @PreAuthorize("hasAnyRole('ROLE_SITE_ADMIN', 'ROLE_RESTAURANT_ADMIN')")
    @RequestMapping(path = "", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FullFoodDTO> createFood(@Valid @RequestBody CreateFoodDTO dto) {
        log.info("POST Food");
        var food = service.createNewFood(dto);
        var uri = URI.create(MAPPING + "/" + food.id);
        return  ResponseEntity.created(uri).body(food);
    }

    @RequestMapping(path = "", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ShortFoodDTO>> getAllFood() {
        log.info("GET Food");
        var food = service.getAllFood();
        if(food.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(food);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FullFoodDTO> getFoodById(@PathVariable Integer id) {
        log.info("GET Food " + id);
        return ResponseEntity.ok(service.getFood(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_SITE_ADMIN', 'ROLE_RESTAURANT_ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FullFoodDTO> updateFood(@PathVariable Integer id, @Valid @RequestBody UpdateFoodDTO dto) {
        log.info("PUT Restaurant " + id);
        return ResponseEntity.ok(service.updateFood(id, dto));
    }

    @PreAuthorize("hasAnyRole('ROLE_SITE_ADMIN', 'ROLE_RESTAURANT_ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFood(@PathVariable Integer id) {
        log.info("DELETE Food " + id);
        service.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_SITE_ADMIN', 'ROLE_RESTAURANT_ADMIN')")
    @RequestMapping(path = "/{id}/tags", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FullFoodDTO> updateRestaurantTags(@PathVariable Integer id,
                                                                  @Valid @RequestBody UpdateTagsDTO dto) {
        log.info("PUT Food " + id + " Tag " + dto.id);
        return ResponseEntity.ok(service.updateFoodTags(id, dto));
    }

    @PreAuthorize("hasAnyRole('ROLE_SITE_ADMIN', 'ROLE_RESTAURANT_ADMIN')")
    @RequestMapping(path = "/{fId}/tags/{tId}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FullFoodDTO> deleteRestaurantTag(@PathVariable Integer fId,
                                                                 @PathVariable Integer tId) {
        log.info("DELETE Food " + fId + " Tag " + tId);
        return ResponseEntity.ok(service.deleteFoodTags(fId, tId));
    }
}