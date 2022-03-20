package com.example.spanacoverflow.repository;

import com.example.spanacoverflow.model.Answer;
import com.example.spanacoverflow.model.AnswerVotes;
import com.example.spanacoverflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAnswerVoteRepository extends JpaRepository<AnswerVotes, Long> {

    public Optional<AnswerVotes> findByUserAndAnswer(User user, Answer answer);
}
