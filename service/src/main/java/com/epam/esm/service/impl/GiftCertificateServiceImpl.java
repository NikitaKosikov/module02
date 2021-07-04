package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.GiftCertificateValidator;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDAO giftCertificateDAO;
    private final GiftCertificateValidator giftCertificateValidator;
    private final TagValidator tagValidator;
    private final GiftCertificateTagDao giftCertificateTagDao;
    private final TagService tagService;


    @Autowired
    public GiftCertificateServiceImpl(@Qualifier("SQLGiftCertificateDAO") GiftCertificateDAO giftCertificateDAO,
                                      GiftCertificateValidator giftCertificateValidator,
                                      TagValidator tagValidator, GiftCertificateTagDao giftCertificateTagDao,
                                      TagService tagService) {
        this.giftCertificateDAO = giftCertificateDAO;
        this.giftCertificateValidator = giftCertificateValidator;
        this.tagValidator = tagValidator;
        this.giftCertificateTagDao = giftCertificateTagDao;
        this.tagService = tagService;
    }

    @Override
    @Transactional
    public GiftCertificate insert(GiftCertificate giftCertificate, Tag newTag){
        giftCertificateValidator.validate(giftCertificate);

        LocalDateTime currentDateTime = LocalDateTime.now();
        giftCertificate.setCreateDate(currentDateTime);
        giftCertificate.setLastUpdateDate(currentDateTime);

        giftCertificate = giftCertificateDAO.insert(giftCertificate);

        if (!"".equals(newTag.getName())) {
            Tag tag = tagService.findTagByName(newTag.getName());
            if (tag==null){
                tag = tagService.insert(newTag);
            }
            giftCertificateTagDao.createConnection(giftCertificate.getId(), tag.getId());
        }

        return giftCertificate;
     }

    @Override
    public void update(GiftCertificate giftCertificate, long certificateId){
        giftCertificateValidator.validate(giftCertificate);

        LocalDateTime currentDateTime = LocalDateTime.now();
        giftCertificate.setLastUpdateDate(currentDateTime);

        giftCertificateDAO.update(giftCertificate, certificateId);
    }

    @Override
    @Transactional
    public void delete(long certificateId) {
        giftCertificateTagDao.deleteConnectionByGiftCertificateId(certificateId);
        giftCertificateDAO.delete(certificateId);
    }

    @Override
    public List<GiftCertificate> read() {
        return giftCertificateDAO.read();
    }

    @Override
    public GiftCertificate readById(long id) {
        return giftCertificateDAO.readById(id);
    }

    @Override
    public void addTag(long tagId, Tag newTag){

       tagValidator.validateName(newTag.getName());

        if (!"".equals(newTag.getName())) {
            Tag tag = tagService.findTagByName(newTag.getName());
            if (tag==null){
                tag = tagService.insert(newTag);
            }
            giftCertificateTagDao.createConnection(tagId, tag.getId());
        }

    }

    @Override
    public List<GiftCertificate> findCertificatesByTagName(String tagName) {
        return giftCertificateDAO.findCertificatesByTagName(tagName);
    }

    @Override
    public List<GiftCertificate> findCertificatesByName(String certificateName) {
        return giftCertificateDAO.findCertificatesByName(certificateName);
    }

    @Override
    public List<GiftCertificate> findCertificatesByDescription(String certificateDescription) {
        return giftCertificateDAO.findCertificatesByDescription(certificateDescription);
    }

    @Override
    @Transactional
    public void updateCertificateTagById(long certificateId, long tagId, String tagName){
     tagValidator.validateName(tagName);

        if (!"".equals(tagName)) {
            Tag tag = tagService.findTagByName(tagName);
            if (tag==null){
                tag = tagService.insert(new Tag(tagName));
            }
            giftCertificateTagDao.updateCertificateTag(certificateId, tagId, tag.getId());
        }

    }

    @Override
    public void deleteCertificateTagById(long certificateId, long tagId) {
        giftCertificateDAO.deleteCertificateTagById(certificateId, tagId);
    }
}
