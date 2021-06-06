package com.epam.esm.impl;

import com.epam.esm.GiftCertificate;
import com.epam.esm.GiftCertificateDAO;
import com.epam.esm.GiftCertificateService;
import com.epam.esm.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDAO giftCertificateDAO;

    @Autowired
    public GiftCertificateServiceImpl(@Qualifier("SQLGiftCertificateDAO") GiftCertificateDAO giftCertificateDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
    }

    @Override
    public void insert(GiftCertificate giftCertificate) {
        giftCertificateDAO.insert(giftCertificate);
    }

    @Override
    public void update(GiftCertificate giftCertificate, int id) {
        giftCertificateDAO.update(giftCertificate, id);
    }

    @Override
    public void delete(int id) {
        giftCertificateDAO.delete(id);
    }

    @Override
    public List<GiftCertificate> read() {
        return giftCertificateDAO.read();
    }

    @Override
    public GiftCertificate readById(int id) {
        return giftCertificateDAO.readById(id);
    }

    @Override
    public void addTag(int id, Tag tag) {
        giftCertificateDAO.addTag(id, tag);
    }
}
