package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface GiftCertificateDAO {

    /**
     * Create new gift certificate
     *
     * @param giftCertificate the gift certificate which will be created
     * @return created gift certifciate
     */
    GiftCertificate insert(GiftCertificate giftCertificate);

    /**
     * Update giftCertificate
     *
     * @param giftCertificate the new gift certificate
     * @param id the id of gift certificate which should be update
     */
    void update(GiftCertificate giftCertificate, long id);

    /**
     * Delete gift certificate by id
     *
     * @param id the id of gift certificate which will be deleted
     */
    void delete(long id);

    /**
     * Delete tag of gift certificate by id
     *
     * @param certificateId the id of gift certificate that we ate deleting the tag for
     * @param tagId the id of tag which will be delete
     */
    void deleteCertificateTagById(long certificateId, long tagId);

    /**
     * Looking for all gift certificates
     *
     * @return list of found gift certificates
     */
    List<GiftCertificate> read();

    /**
     * Looking for gift certificate by id
     *
     * @param id the id of gift certificate
     * @return the found gift certificate
     */
    GiftCertificate readById(long id);

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
}
