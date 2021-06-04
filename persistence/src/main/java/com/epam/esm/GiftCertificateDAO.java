package com.epam.esm;

public interface GiftCertificateDAO {
    void insert(GiftCertificate giftCertificate);
    void update(GiftCertificate giftCertificate, int id);
    void delete(int id);
    List<GiftCertificate> read();
    GiftCertificate readById(int id);
}
