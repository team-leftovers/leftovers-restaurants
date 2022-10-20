package com.leftovers.restaurants.controller;

import com.leftovers.restaurants.dto.*;
import com.leftovers.restaurants.service.TagService;
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
@RequestMapping(path = "/tags")
@RequiredArgsConstructor
public class TagController {
    private static final String MAPPING = "/tag";
    private final TagService service;


    @PreAuthorize("hasAnyRole('ROLE_SITE_ADMIN', 'ROLE_RESTAURANT_ADMIN')")
    @RequestMapping(path = "", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TagDTO> createTag(@Valid @RequestBody CreateTagDTO dto) {
        log.info("POST Tag");
        var tag = service.createNewTag(dto);
        var uri = URI.create(MAPPING + "/" + tag.id);
        return  ResponseEntity.created(uri).body(tag);
    }

    @RequestMapping(path = "", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<TagDTO>> getAllTags() {
        log.info("GET Tags");
        var food = service.getAllTags();
        if(food.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(food);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TagDTO> getTagById(@PathVariable Integer id) {
        log.info("GET Tag " + id);
        return ResponseEntity.ok(service.getTag(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_SITE_ADMIN', 'ROLE_RESTAURANT_ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TagDTO> updateFood(@PathVariable Integer id, @Valid @RequestBody UpdateTagDTO dto) {
        log.info("PUT Tag " + id);
        return ResponseEntity.ok(service.updateTag(id, dto));
    }

    @PreAuthorize("hasAnyRole('ROLE_SITE_ADMIN', 'ROLE_RESTAURANT_ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFood(@PathVariable Integer id) {
        log.info("DELETE Tag " + id);
        service.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
