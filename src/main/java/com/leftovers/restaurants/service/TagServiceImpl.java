package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.CreateTagDTO;
import com.leftovers.restaurants.dto.TagDTO;
import com.leftovers.restaurants.dto.UpdateTagDTO;
import com.leftovers.restaurants.exception.NoSuchTagException;
import com.leftovers.restaurants.mapper.TagMapper;
import com.leftovers.restaurants.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepo;

    @Transactional
    @Override
    public TagDTO createNewTag(CreateTagDTO dto) {
        var tag = tagRepo.save(TagMapper.toTag(dto));
        return TagMapper.toDto(tag);
    }

    @Override
    public List<TagDTO> getAllTags() {
        return tagRepo.findAll().stream()
                .map(item -> TagMapper.toDto(item))
                .collect(Collectors.toList());
    }

    @Override
    public TagDTO getTag(Integer id) {
        var tag = tagRepo.findTagById(id)
                .orElseThrow(() -> new NoSuchTagException(id));
        return TagMapper.toDto(tag);
    }

    @Transactional
    @Override
    public TagDTO updateTag(Integer id, UpdateTagDTO dto) {
        var tag = tagRepo.findTagById(id)
                .orElseThrow(() -> new NoSuchTagException(id));

        ifNotNull(dto.name, tag::setName);

        return TagMapper.toDto(tagRepo.save(tag));
    }

    @Transactional
    @Override
    public void deleteTag(Integer id) {
        if(tagRepo.deleteTagById(id) == 0)
            throw new NoSuchTagException(id);
    }

    // Utility function to execute function if value not null
    private <T> void ifNotNull(T val, Consumer<T> func) {
        if(val != null)
            func.accept(val);
    }
}
