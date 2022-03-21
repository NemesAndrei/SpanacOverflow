package com.example.spanacoverflow.service;

import com.example.spanacoverflow.model.Answer;
import com.example.spanacoverflow.model.AnswerVotes;
import com.example.spanacoverflow.model.User;
import com.example.spanacoverflow.repository.IAnswerVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerVotesService {

    @Autowired
    IAnswerVoteRepository iAnswerVoteRepository;
    @Autowired
    UserService userService;
    @Autowired
    AnswerService answerService;

    public List<AnswerVotes> getAllAnswerVotes() {
        return (List<AnswerVotes>) iAnswerVoteRepository.findAll();
    }

    public AnswerVotes getAnswerVote(Long id) {
        Optional<AnswerVotes> answerVotes = iAnswerVoteRepository.findById(id);
        return answerVotes.orElse(null);
    }

    public String deleteAnswerVote(Long id) {
        try {
            iAnswerVoteRepository.delete(this.getAnswerVote(id));
            return "Delete success";
        } catch (Exception e) {
            return "Delete failed";
        }
    }

    public AnswerVotes saveAnswerVote(AnswerVotes answerVotes) {
        return iAnswerVoteRepository.save(answerVotes);
    }

    public String voteAnswer(Long answerId, Long userId, Integer vote) {
        User user = userService.getUser(userId);
        Answer answer = answerService.getAnswer(answerId);
        if (answer.getUser().getId().equals(userId)) {
            return "User cannot vote their own answer!";
        }
        Optional<AnswerVotes> answerVotes = iAnswerVoteRepository.findByUserAndAnswer(user, answer);
        if (!answerVotes.isPresent()) {
            return createAnswerVoteIfNotExistent(answerId, vote, user, answer);
        }
        if (answerVotes.get().getVote().equals(vote)) {
            return "User cannot vote twice on the same answer!";
        }

        return changeAnswerIfExistent(answerId, vote, answerVotes);
    }

    private String changeAnswerIfExistent(Long answerId, Integer vote, Optional<AnswerVotes> answerVotes) {
        answerService.updateAnswerVotes(answerId, (-1) * (answerVotes.get().getVote()));
        answerVotes.get().setVote(vote);
        answerService.updateAnswerVotes(answerId, vote);
        this.saveAnswerVote(answerVotes.get());
        return "Vote changed successfully!";
    }

    private String createAnswerVoteIfNotExistent(Long answerId, Integer vote, User user, Answer answer) {
        AnswerVotes answerVotes1 = new AnswerVotes();
        answerVotes1.setUser(user);
        answerVotes1.setVote(vote);
        answerVotes1.setAnswer(answer);
        answerService.updateAnswerVotes(answerId, vote);
        saveAnswerVote(answerVotes1);
        return "Vote successful!";
    }

}
