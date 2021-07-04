package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service
public class TagServiceImpl implements TagService {

    private final TagDAO tagDAO;
    private final GiftCertificateTagDao giftCertificateTagDao;
    private final TagValidator tagValidator;

    @Autowired
    public TagServiceImpl(@Qualifier("SQLTagDAO") TagDAO tagDAO, GiftCertificateTagDao giftCertificateTagDao, TagValidator tagValidator) {
        this.tagDAO = tagDAO;
        this.giftCertificateTagDao = giftCertificateTagDao;
        this.tagValidator = tagValidator;
    }

    @Override
    public Tag insert(Tag tag) {
        tagValidator.validateName(tag.getName());
        return tagDAO.insert(tag);
    }

    @Override
    public void update(Tag tag, long id) {
        tagValidator.validateName(tag.getName());
        tagDAO.update(tag, id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        giftCertificateTagDao.deleteConnectionByTagId(id);
        tagDAO.delete(id);
    }

    @Override
    public List<Tag> read() {
        return tagDAO.read();
    }

    @Override
    public Tag readById(long id) {
        return tagDAO.readById(id);
    }

    @Override
    public List<Tag> readByCertificateId(long id) {
        return tagDAO.readByCertificateId(id);
    }

    @Override
    public Tag findTagByName(String tagName) {
        return tagDAO.findTagByName(tagName);
    }
}
