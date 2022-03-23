package com.example.spanacoverflow;

import com.example.spanacoverflow.model.Tag;
import com.example.spanacoverflow.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Objects;

@SpringBootTest
@Transactional
public class TagTest {

    @Autowired
    TagService tagService;

    @Test
    void testSaveTag() {
        Tag tag = new Tag();
        tag.setName("UnTagDeTest");
        Tag savedTag = tagService.saveTag(tag);
        assert (tagService.getTag(savedTag.getId()).getName().equals("UnTagDeTest"));
    }

    @Test
    void testUpdateTag() {
        Tag tag = new Tag();
        tag.setName("UnTagDeTest");
        Tag savedTag = tagService.saveTag(tag);
        tagService.updateTag(savedTag.getId(), "UnAltTagDeTest");
        assert (tagService.getTag(savedTag.getId()).getName().equals("UnAltTagDeTest"));
    }

    @Test
    void testDeleteTag() {
        Tag tag = new Tag();
        tag.setName("UnTagDeTest");
        Tag savedTag = tagService.saveTag(tag);
        String s = tagService.deleteTag(savedTag.getId());
        assert (Objects.equals(s,"Delete success"));
        assert (tagService.getTag(savedTag.getId()) == null);
    }
}
