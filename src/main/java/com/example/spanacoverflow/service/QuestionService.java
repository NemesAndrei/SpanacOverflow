package com.example.spanacoverflow.service;

import com.example.spanacoverflow.model.Question;
import com.example.spanacoverflow.model.Tag;
import com.example.spanacoverflow.model.User;
import com.example.spanacoverflow.repository.IQuestionRepository;
import com.example.spanacoverflow.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    @Autowired
    IQuestionRepository iQuestionRepository;

    @Autowired
    TagService tagService;

    @Autowired
    UserService userService;

    public List<Question> getAllQuestions() {
        return (List<Question>) iQuestionRepository.findAllByOrderByCreatedDesc();
    }

    public Question getQuestion(Long id) {
        Optional<Question> questionOptional = iQuestionRepository.findById(id);
        return questionOptional.orElse(null);
    }

    public List<Question> getAllQuestionsFilteredByTitle(String title) {
        return (List<Question>) iQuestionRepository.findAllByTitleContainingIgnoreCase(title);
    }

    public List<Question> getAllQuestionsFilteredByTag(String tag) {
        Tag tagNew = tagService.getTagByName(tag);
        List<Question> questionList = iQuestionRepository.findAll();
        questionList.removeIf(question -> !question.getTags().contains(tagNew));
        return questionList;
    }

    public String deleteQuestion(Long id) {
        try {
            iQuestionRepository.delete(this.getQuestion(id));
            return "Delete success";
        } catch (Exception e) {
            return "Delete failed";
        }
    }

    public Question saveQuestion(Question question, List<String> tags, Long userId) {

        User user = userService.getUser(userId);
        Set<Tag> tagList = new HashSet<>();
        for (String tag : tags) {
            Tag tagNew = tagService.getTagByName(tag);
            if (tagNew != null) {
                tagList.add(tagNew);
            } else {
                tagNew = new Tag();
                tagNew.setName(tag);
                tagService.saveTag(tagNew);
                tagList.add(tagNew);
            }
        }
        question.setTags(tagList);
        question.setUser(user);
        question.setCreated(DateUtils.getDate());
        question.setVotes(0);
        return iQuestionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question question) {
        Question initialQuestion = this.getQuestion(id);
        initialQuestion.setBody(question.getBody());
        initialQuestion.setTags(question.getTags());
        return iQuestionRepository.save(initialQuestion);
    }

    public Question updateQuestionVotes(Long id, Integer vote) {
        Question question = this.getQuestion(id);
        question.setVotes(question.getVotes() + vote);
        return iQuestionRepository.save(question);
    }
}
