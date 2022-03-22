package com.example.spanacoverflow.controller;

import com.example.spanacoverflow.model.Question;
import com.example.spanacoverflow.service.AnswerService;
import com.example.spanacoverflow.service.QuestionService;
import com.example.spanacoverflow.service.QuestionVotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionVotesService questionVotesService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<Question> getQuestions() {
        return questionService.getAllQuestions();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/save")
    @ResponseBody
    public Question saveQuestion(@RequestBody Question question, @RequestParam(name = "tags") List<String> tags, @RequestParam(name = "userid") Long userId) {
        return questionService.saveQuestion(question, tags, userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filterTitle")
    @ResponseBody
    public List<Question> getQuestionsFilteredByTitle(@RequestParam(name = "title") String title) {
        return questionService.getAllQuestionsFilteredByTitle(title);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filterTag")
    @ResponseBody
    public List<Question> getQuestionsFilteredByTag(@RequestParam(name = "tag") String tag) {
        return questionService.getAllQuestionsFilteredByTag(tag);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    @ResponseBody
    public Question getQuestionById(@RequestParam(name = "questionid") Long id) {
        return questionService.getQuestion(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vote")
    @ResponseBody
    public String voteQuestionById(@RequestParam(name = "questionid") Long questionId, @RequestParam(name = "userid") Long userId, @RequestParam(name = "vote") Integer vote) {
        return questionVotesService.voteQuestion(questionId, userId, vote);
    }

/*    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    @ResponseBody
    public String deleteQuestion(@RequestParam(name = "questionid") Long questionId) {
        return questionService.deleteQuestion(questionId);
    }*/
}

