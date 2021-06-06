package com.epam.esm;

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
     */
    void update(GiftCertificate giftCertificate, int id);

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
}
