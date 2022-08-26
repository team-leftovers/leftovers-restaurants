package com.leftovers.restaurants;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import java.util.Optional;

@Slf4j
public class DeleteResultHandler implements ResultHandler {
    private JpaRepository<Object, Integer> repository;

    public DeleteResultHandler(JpaRepository repository) {
        this.repository = repository;
    }

    public static DeleteResultHandler deleteResult(JpaRepository repository) {
        return new DeleteResultHandler(repository);
    }

    @Override
    public void handle(MvcResult result) throws Exception {
        String location = result.getResponse().getHeader("Location");
        Integer id = Integer.parseInt(location.substring(location.lastIndexOf("/") + 1));
        Optional<Object> o = repository.findById(id);
        if (!o.isPresent())
            return;
        log.error("found");
        repository.delete(o.get());
    }
}