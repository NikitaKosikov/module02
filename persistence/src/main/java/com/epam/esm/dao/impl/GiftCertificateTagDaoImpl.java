package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GiftCertificateTagDaoImpl implements GiftCertificateTagDao {
    private static final String INSERT_GIFT_CERTIFICATE_TAG_CONNECTION_SQL = "INSERT INTO "
            + "m2m_gift_certificate_tag (gift_certificate_id, tag_id) VALUES (?, ?)";
    private static final String SELECT_TAGS_BY_GIFT_CERTIFICATE_ID_SQL = "SELECT tag.id, tag.name"
            + " FROM m2m_gift_certificate_tag JOIN tag ON "
            + "m2m_gift_certificate_tag.tag_id = tag.id WHERE gift_certificate_id = ? ";
    private static final String DELETE_CONNECTION_BY_TAG_ID_SQL = "DELETE FROM "
            + "m2m_gift_certificate_tag WHERE tag_id = ?";
    private static final String DELETE_CONNECTION_BY_GIFT_CERTIFICATE_ID_SQL = "DELETE FROM "
            + "m2m_gift_certificate_tag WHERE gift_certificate_id = ?";

    private static final String UPDATE_CERTIFICATE_TAG = "UPDATE m2m_gift_certificate_tag SET tag_id=? " +
            "WHERE gift_certificate_id=? AND tag_id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateTagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createConnection(long giftCertificateId,long tagId) {
        jdbcTemplate.update(INSERT_GIFT_CERTIFICATE_TAG_CONNECTION_SQL, giftCertificateId, tagId);
    }

    @Override
    public void deleteConnectionByGiftCertificateId(long id) {
        jdbcTemplate.update(DELETE_CONNECTION_BY_GIFT_CERTIFICATE_ID_SQL, id);
    }

    @Override
    public boolean deleteConnectionByTagId(long id) {
        return jdbcTemplate.update(DELETE_CONNECTION_BY_TAG_ID_SQL, id) > 0;
    }

    @Override
    public void updateCertificateTag(long certificateId, long oldTagId, long newTagId) {
        jdbcTemplate.update(UPDATE_CERTIFICATE_TAG, newTagId, certificateId, oldTagId);
    }
}
