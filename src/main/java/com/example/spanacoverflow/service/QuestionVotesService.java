package com.example.spanacoverflow.service;

import com.example.spanacoverflow.model.*;
import com.example.spanacoverflow.repository.IAnswerVoteRepository;
import com.example.spanacoverflow.repository.IQuestionRepository;
import com.example.spanacoverflow.repository.IQuestionVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionVotesService {

    @Autowired
    IQuestionVoteRepository iQuestionVoteRepository;
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    public List<QuestionVotes> getAllQuestionVotes() {
        return (List<QuestionVotes>) iQuestionVoteRepository.findAll();
    }

    public QuestionVotes getQuestionVote(Long id) {
        Optional<QuestionVotes> questionVotes = iQuestionVoteRepository.findById(id);
        return questionVotes.orElse(null);
    }

    public String deleteQuestionVote(Long id) {
        try {
            iQuestionVoteRepository.delete(this.getQuestionVote(id));
            return "Delete success";
        } catch (Exception e) {
            return "Delete failed";
        }
    }

    public QuestionVotes saveQuestionVote(QuestionVotes questionVotes) {
        return iQuestionVoteRepository.save(questionVotes);
    }

    public QuestionVotes updateQuestionVote(Long id, QuestionVotes questionVotes) {
        QuestionVotes questionVote = this.getQuestionVote(id);
        questionVote.setVote(questionVote.getVote());
        return iQuestionVoteRepository.save(questionVote);
    }

    public String voteQuestion(Long questionId, Long userId, Integer vote) {
        User user = userService.getUser(userId);
        Question question = questionService.getQuestion(questionId);
        if(question.getUser().getId().equals(userId)) {
            return "User cannot vote their own question!";
        }
        Optional<QuestionVotes> questionVotes = iQuestionVoteRepository.findByUserAndQuestion(user, question);
        if(!questionVotes.isPresent()) {
            QuestionVotes questionVotes1 = new QuestionVotes();
            questionVotes1.setUser(user);
            questionVotes1.setVote(vote);
            questionVotes1.setQuestion(question);
            questionService.updateQuestionVotes(questionId,vote);
            saveQuestionVote(questionVotes1);
            return "Vote successful!";
        }
        if(questionVotes.get().getVote().equals(vote)) {
            return "User cannot vote twice on the same question!";
        }
        questionService.updateQuestionVotes(questionId,(-1)*(questionVotes.get().getVote()));
        questionVotes.get().setVote(vote);
        questionService.updateQuestionVotes(questionId, vote);
        this.saveQuestionVote(questionVotes.get());
        return "Vote changed successfully!";
    }
}
