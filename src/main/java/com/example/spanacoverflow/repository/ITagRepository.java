package com.example.spanacoverflow.repository;

import com.example.spanacoverflow.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ITagRepository extends JpaRepository<Tag, Long> {

    public Optional<Tag> findByName(String name);
}
