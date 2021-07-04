package com.epam.esm.dao;

public interface GiftCertificateTagDao {
    /**
     * Add gift certificate and tag connections in database
     *
     * @param giftCertificateId gift certificate to add connections
     * @param tagId the list of tags to add connections
     */
    void createConnection(long giftCertificateId, long tagId);

    /**
     * Remove gift certificate and tag connections in database by gift certificate
     * id
     *
     * @param id the id of gift certificate to remove connection
     */
    void deleteConnectionByGiftCertificateId(long id);

    /**
     * Remove gift certificate and tag connections in database by tag id
     *
     * @param id the id of tag to remove connection
     * @return boolean true if everything go correct, else false
     */
    boolean deleteConnectionByTagId(long id);

    /**
     * Update tag of gift certificate and tag connection in database by certificate id
     *
     * @param certificateId the id of gift certificate for which the tag will be changed
     * @param oldTagId the id of old tag
     * @param newTagId the new id tag
     */
    void updateCertificateTag(long certificateId, long oldTagId, long newTagId);
}
