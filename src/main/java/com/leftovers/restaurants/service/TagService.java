package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.CreateTagDTO;
import com.leftovers.restaurants.dto.TagDTO;
import com.leftovers.restaurants.dto.UpdateTagDTO;

import java.util.List;

public interface TagService {
    TagDTO createNewTag(CreateTagDTO dto);
    List<TagDTO> getAllTags();
    TagDTO getTag(Integer id);
    TagDTO updateTag(Integer id, UpdateTagDTO dto);
    void deleteTag(Integer id);
}
