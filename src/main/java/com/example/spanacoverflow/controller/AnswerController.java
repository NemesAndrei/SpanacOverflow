package com.example.spanacoverflow.controller;

import com.example.spanacoverflow.model.Answer;
import com.example.spanacoverflow.model.Question;
import com.example.spanacoverflow.service.AnswerService;
import com.example.spanacoverflow.service.AnswerVotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//sss
@Controller
@RequestMapping(value = "/answers")
@CrossOrigin(origins = "http://localhost:4200")
public class AnswerController {

    @Autowired
    AnswerService answerService;
    @Autowired
    AnswerVotesService answerVotesService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<Answer> getAnswers() {
        return answerService.getAllAnswers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getForQuestion")
    @ResponseBody
    public List<Answer> getAnswersForQuestion(@RequestParam(name = "questionid") Long questionId) {
        return answerService.getAllAnswersFilteredByQuestion(questionId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getForUser")
    @ResponseBody
    public List<Answer> getAnswersForUser(@RequestParam(name = "userid") Long userId) {
        return answerService.getAllAnswersFilteredByUser(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/save")
    @ResponseBody
    public Answer saveAnswer(@RequestBody Answer answer, @RequestParam(name = "questionid") Long questionId, @RequestParam(name = "userid") Long userId) {
        return answerService.saveAnswer(answer, questionId, userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    @ResponseBody
    public Answer updateAnswer(@RequestBody String body, @RequestParam(name = "answerid") Long answerId) {
        return answerService.updateAnswer(answerId, body);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    @ResponseBody
    public String deleteAnswer(@RequestParam(name = "answerid") Long answerId) {
        return answerService.deleteAnswer(answerId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vote")
    @ResponseBody
    public String voteAnswerById(@RequestParam(name = "answerid") Long answerId, @RequestParam(name = "userid") Long userId, @RequestParam(name = "vote") Integer vote) {
        return answerVotesService.voteAnswer(answerId, userId, vote);
    }

}
