package com.epam.esm;

import java.util.List;

public interface GiftCertificateDAO {
    void insert(GiftCertificate giftCertificate);
    void update(GiftCertificate giftCertificate, int id);
    void delete(int id);
    List<GiftCertificate> read();
    GiftCertificate readById(int id);
    void addTag(int id, Tag tag);
}
