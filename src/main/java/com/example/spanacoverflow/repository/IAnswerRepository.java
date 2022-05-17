package com.example.spanacoverflow.repository;

import com.example.spanacoverflow.model.Answer;
import com.example.spanacoverflow.model.Question;
import com.example.spanacoverflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IAnswerRepository extends JpaRepository<Answer, Long> {
    public Iterable<Answer> findAllByOrderByVotesDesc();

    public Iterable<Answer> findAllByUser(User user);

    public Iterable<Answer> findAllByQuestion(Question question);
}
