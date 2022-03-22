package com.example.spanacoverflow.service;

import com.example.spanacoverflow.model.Answer;
import com.example.spanacoverflow.model.Question;
import com.example.spanacoverflow.model.User;
import com.example.spanacoverflow.repository.IAnswerRepository;
import com.example.spanacoverflow.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    IAnswerRepository iAnswerRepository;
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    public List<Answer> getAllAnswers() {
        return (List<Answer>) iAnswerRepository.findAllByOrderByVotesDesc();
    }

    public Answer getAnswer(Long id) {
        Optional<Answer> answerOptional = iAnswerRepository.findById(id);
        return answerOptional.orElse(null);
    }

    public String deleteAnswer(Long id) {
        try {
            iAnswerRepository.delete(this.getAnswer(id));
            return "Delete success";
        } catch (Exception e) {
            return "Delete failed";
        }
    }

    public Answer saveAnswer(Answer answer, Long questionId, Long userId) {
        Question question = questionService.getQuestion(questionId);
        User user = userService.getUser(userId);
        answer.setUser(user);
        answer.setQuestion(question);
        answer.setCreated(DateUtils.getDate());
        answer.setVotes(0);
        return iAnswerRepository.save(answer);
    }

    public Answer updateAnswer(Long id, String body) {
        Answer initialAnswer = this.getAnswer(id);
        initialAnswer.setBody(body);
        return iAnswerRepository.save(initialAnswer);
    }

    public Answer updateAnswerVotes(Long id, Integer vote) {
        Answer initialAnswer = this.getAnswer(id);
        initialAnswer.setVotes(initialAnswer.getVotes() + vote);
        return iAnswerRepository.save(initialAnswer);
    }

}
