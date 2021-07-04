package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock
    private TagDAO tagDAO;

    @InjectMocks
    private TagServiceImpl tagService;


    @Test
    void insert() {
        Tag tag = new Tag();
        tag.setName("TestName");
        tag.setId(1L);
        when(tagDAO.insert(any(Tag.class))).thenReturn(tag);
        Tag foundTag = tagDAO.insert(tag);
        assertThat(tag.getId()).isNotZero();
        assertEquals(foundTag, tag);
    }

    @Test
    void update() {
        Tag tag = new Tag();
        doNothing().when(tagDAO).update(any(Tag.class), anyLong());
        tagDAO.update(tag, 0);
        verify(tagDAO, times(1)).update(tag, 0);
    }

    @Test
    void delete() {
        doNothing().when(tagDAO).delete(anyLong());
        tagDAO.delete(0);
        verify(tagDAO, times(1)).delete(0);
    }

    @Test
    void read() {
        when(tagDAO.read()).thenReturn(new ArrayList<>());
        List<Tag> foundTags = tagService.read();

        assertNotNull(foundTags);
    }

    @Test
    void readById() {
        when(tagDAO.readById(0)).thenReturn(new Tag());
        Tag fountTag = tagService.readById(0);

        assertNotNull(fountTag);
    }

    @Test
    void readByCertificateId() {
        when(tagDAO.readByCertificateId(5)).thenReturn(new ArrayList<>());
        List<Tag> foundTags = tagService.readByCertificateId(5);

        assertNotNull(foundTags);
    }
}