package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface GiftCertificateService {

    /**
     * Insert giftCertificate in database.
     *
     * @param giftCertificate the gift certificate.
     */
    void insert(GiftCertificate giftCertificate);

    /**
     *Update gift certificate in database by specific id.
     *
     * @param giftCertificate the gift certificate.
     * @param id the id of the gift certificate.
     * @param tagName the name of tag.
     */
    void update(GiftCertificate giftCertificate, int id, String tagName);

    /**
     * Delete the gift certificate by specific id.
     *
     * @param id the id of the gift certificate.
     */
    void delete(int id);

    /**
     * Looking for a set of gift certificates.
     *
     * @return set of found gift certificates.
     */
    List<GiftCertificate> read();

    /**
     * Looking for the gift certificate by specific id.
     *
     * @param id the id of gift certificate.
     * @return found gift certificate.
     */
    GiftCertificate readById(int id);

    /**
     * Adding tag in database.
     *
     * @param id the id of the tag.
     * @param tag the tag.
     */
    void addTag(int id, Tag tag);

    List<GiftCertificate> findCertificatesByTagName(String tagName);
}
