package com.example.spanacoverflow.service;

import com.example.spanacoverflow.model.Answer;
import com.example.spanacoverflow.model.AnswerVotes;
import com.example.spanacoverflow.model.User;
import com.example.spanacoverflow.repository.IAnswerVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AnswerVotesService {

    @Autowired
    IAnswerVoteRepository iAnswerVoteRepository;
    @Autowired
    UserService userService;
    @Autowired
    AnswerService answerService;

    public List<AnswerVotes> getAllAnswerVotes(){
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

    public AnswerVotes updateAnswerVote(Long id, AnswerVotes answerVotes) {
        AnswerVotes answerVotes1 = this.getAnswerVote(id);
        answerVotes1.setVote(answerVotes.getVote());
        return iAnswerVoteRepository.save(answerVotes1);
    }

    public String voteAnswer(Long answerId, Long userId, Integer vote) {
        User user = userService.getUser(userId);
        Answer answer = answerService.getAnswer(answerId);
        if(answer.getUser().getId()==userId) {
            return "User cannot vote their own answer!";
        }
        Optional<AnswerVotes> answerVotes = iAnswerVoteRepository.findByUserAndAnswer(user, answer);
        if(!answerVotes.isPresent()) {
            AnswerVotes answerVotes1 = new AnswerVotes();
            answerVotes1.setUser(user);
            answerVotes1.setVote(vote);
            answerVotes1.setAnswer(answer);
            answerService.updateAnswerVotes(answerId,vote);
            saveAnswerVote(answerVotes1);
            return "Vote successful!";
        }
        if(answerVotes.get().getVote().equals(vote)) {
            return "User cannot vote twice on the same answer!";
        }

        answerService.updateAnswerVotes(answerId,(-1)*(answerVotes.get().getVote()));
        answerVotes.get().setVote(vote);
        answerService.updateAnswerVotes(answerId, vote);
        this.saveAnswerVote(answerVotes.get());
        return "Vote changed successfully!";
    }

}
