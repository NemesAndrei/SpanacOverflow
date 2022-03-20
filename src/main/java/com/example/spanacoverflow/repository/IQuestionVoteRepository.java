package com.example.spanacoverflow.repository;

import com.example.spanacoverflow.model.AnswerVotes;
import com.example.spanacoverflow.model.Question;
import com.example.spanacoverflow.model.QuestionVotes;
import com.example.spanacoverflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IQuestionVoteRepository extends JpaRepository<QuestionVotes, Long> {

    public Optional<QuestionVotes> findByUserAndQuestion(User user, Question question);
}
