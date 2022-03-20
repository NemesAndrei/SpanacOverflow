package com.example.spanacoverflow.repository;

import com.example.spanacoverflow.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IQuestionRepository extends JpaRepository<Question, Long> {
    public Iterable<Question> findAllByOrderByCreatedDesc();

    public Iterable<Question> findAllByTitleContainingIgnoreCase(String title);
}
