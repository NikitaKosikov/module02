package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface GiftCertificateService {

    /**
     * Create giftCertificate in database and tag if the tag not exist.
     *
     * @param giftCertificate the gift certificate.
     * @param newTag the tag for gift certificate.
     */
    GiftCertificate insert(GiftCertificate giftCertificate, Tag newTag);

    /**
     *Update gift certificate in database by specific id.
     *
     * @param giftCertificate the gift certificate.
     * @param certificateId the id of the gift certificate.
     *
     */
    void update(GiftCertificate giftCertificate, long certificateId);

    /**
     * Delete the gift certificate by specific id.
     *
     * @param certificateId the id of the gift certificate.
     */
    void delete(long certificateId);

    /**
     * Looking for a set of gift certificates.
     *
     * @return list of found gift certificates.
     */
    List<GiftCertificate> read();

    /**
     * Looking for the gift certificate by specific id.
     *
     * @param certificateId the id of gift certificate.
     * @return the found gift certificate.
     */
    GiftCertificate readById(long certificateId);

    /**
     * Adding tag in database.
     *
     * @param tagId the id of the tag.
     * @param newTag the tag which will be create.
     */
    void addTag(long tagId, Tag newTag);

    /**
     * Looking for all gift certificates by name of tag
     *
     * @param tagName the name of tag by which we are looking for gift certificates
     * @return list of found gift certificates
     */
    List<GiftCertificate> findCertificatesByTagName(String tagName);

    /**
     * Looking for all gift certificates by name of gift certificate
     *
     * @param certificateName the name of gift certificate by which we are looking for gift certificates
     * @return list of found gift certificates
     */
    List<GiftCertificate> findCertificatesByName(String certificateName);

    /**
     * Looking for all gift certificates by description of gift certificate
     *
     * @param certificateDescription the description of gift certificate by which we are looking for gift certificates
     * @return list of found gift certificates
     */
    List<GiftCertificate> findCertificatesByDescription(String certificateDescription);

    /**
     * Update tag of gift certificate
     *
     * @param certificateId the id of gift certificate that we are updating the tag for
     * @param tagId the id of the tag to be changed
     * @param tagName the new name of tag
     */
    void updateCertificateTagById(long certificateId, long tagId, String tagName);

    /**
     * Delete tag of gift certificate
     *
     * @param certificateId the id of gift certificate that we are deleting the tag for
     * @param tagId the id of the tag to be deleted
     */
    void deleteCertificateTagById(long certificateId, long tagId);
}
