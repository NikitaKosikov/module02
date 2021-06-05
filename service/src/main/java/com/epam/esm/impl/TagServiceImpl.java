package com.epam.esm.impl;

import com.epam.esm.Tag;
import com.epam.esm.TagDAO;
import com.epam.esm.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagServiceImpl implements TagService {

    private final TagDAO tagDAO;

    @Autowired
    public TagServiceImpl(@Qualifier("SQLTagDAO") TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public void insert(Tag tag) {
        tagDAO.insert(tag);
    }

    @Override
    public void update(Tag tag, int id) {
        tagDAO.update(tag, id);
    }

    @Override
    public void delete(int id) {
        tagDAO.delete(id);
    }

    @Override
    public List<Tag> read() {
        return tagDAO.read();
    }

    @Override
    public Tag readById(int id) {
        return tagDAO.readById(id);
    }
}
