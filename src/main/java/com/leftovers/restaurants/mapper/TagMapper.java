package com.leftovers.restaurants.mapper;

import com.leftovers.restaurants.dto.CreateTagDTO;
import com.leftovers.restaurants.dto.TagDTO;
import com.leftovers.restaurants.model.Tag;

public class TagMapper {
    public static Tag toTag(CreateTagDTO dto) {
        return Tag.builder()
                .name(dto.name)
            .build();
    }

    public static TagDTO toDto(Tag tag) {
        return TagDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
            .build();
    }
}
