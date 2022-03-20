package com.example.spanacoverflow.repository;

import com.example.spanacoverflow.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IAnswerRepository extends JpaRepository<Answer, Long> {
    public Iterable<Answer> findAllByOrderByVotesDesc();
}
