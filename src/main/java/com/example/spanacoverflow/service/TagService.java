package com.example.spanacoverflow.service;

import com.example.spanacoverflow.model.Tag;
import com.example.spanacoverflow.repository.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    ITagRepository iTagRepository;

    public List<Tag> getAllTags() {
        return (List<Tag>) iTagRepository.findAll();
    }

    public Tag getTag(Long id) {
        Optional<Tag> tag = iTagRepository.findById(id);
        return tag.orElse(null);
    }

    public Tag getTagByName(String name) {
        Optional<Tag> tag = iTagRepository.findByName(name);
        return tag.orElse(null);
    }

    public String deleteTag(Long id) {
        try {
            iTagRepository.delete(this.getTag(id));
            return "Delete success";
        } catch (Exception e) {
            return "Delete failed";
        }
    }

    public Tag saveTag(Tag tag) {
        return iTagRepository.save(tag);
    }

    public Tag updateTag(Long id, Tag tag) {
        Tag initialTag = this.getTag(id);
        initialTag.setName(tag.getName());
        return iTagRepository.save(initialTag);
    }
}
