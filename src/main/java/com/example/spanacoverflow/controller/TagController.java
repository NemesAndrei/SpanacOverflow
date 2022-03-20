package com.example.spanacoverflow.controller;

import com.example.spanacoverflow.model.Question;
import com.example.spanacoverflow.model.Tag;
import com.example.spanacoverflow.service.QuestionService;
import com.example.spanacoverflow.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/tags")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<Tag> getTags() {
        return tagService.getAllTags();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/save")
    @ResponseBody
    public Tag saveTag(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }
}
