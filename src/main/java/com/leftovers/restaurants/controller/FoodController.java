package com.leftovers.restaurants.controller;

import com.leftovers.restaurants.dto.CreateFoodDTO;
import com.leftovers.restaurants.dto.FullFoodDTO;
import com.leftovers.restaurants.dto.ShortFoodDTO;
import com.leftovers.restaurants.dto.UpdateFoodDTO;
import com.leftovers.restaurants.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FullFoodDTO> updateFood(@PathVariable Integer id, @Valid @RequestBody UpdateFoodDTO dto) {
        log.info("PUT Restaurant " + id);
        return ResponseEntity.ok(service.updateFood(id, dto));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFood(@PathVariable Integer id) {
        log.info("DELETE Food " + id);
        service.deleteFood(id);
        return ResponseEntity.noContent().build();
    }
}
