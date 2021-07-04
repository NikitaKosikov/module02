package com.epam.esm.dao.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.row_mapper.GiftCertificateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Component
public class SQLGiftCertificateDAO implements GiftCertificateDAO {

    private static final String FIND_ALL_GIFT_CERTIFICATE = "SELECT * FROM gift_certificate";

    private static final String INSERT_GIFT_CERTIFICATE = "INSERT INTO gift_certificate " +
            "(name, description, price, duration, create_date, last_update_date) VALUES " +
            "(?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL_GIFT_CERTIFICATE_BY_TAG_NAME = "SELECT * FROM m2m_gift_certificate_tag " +
            "JOIN gift_certificate gc on gc.id = m2m_gift_certificate_tag.gift_certificate_id " +
            "JOIN tag t on t.id = m2m_gift_certificate_tag.tag_id " +
            "WHERE t.name regexp ? ORDER BY gc.name";

    private static final String FIND_ALL_GIFT_CERTIFICATE_BY_CERTIFICATE_NAME = "SELECT * FROM gift_certificate " +
            "WHERE gift_certificate.name regexp ? ORDER BY gift_certificate.name";

    private static final String FIND_ALL_GIFT_CERTIFICATE_BY_DESCRIPTION = "SELECT * FROM gift_certificate " +
            "WHERE gift_certificate.description regexp ? ORDER BY gift_certificate.name";

    private static final String FIND_GIFT_CERTIFICATE_BY_ID = "SELECT * FROM gift_certificate WHERE id =?";

    private static final String UPDATE_GIFT_CERTIFICATION = "UPDATE gift_certificate SET " +
            "name = ?, description=?, price=?, duration=?, last_update_date=? " +
            "WHERE id=?";

    private static final String DELETE_GIFT_CERTIFICATION_BY_ID = "DELETE FROM gift_certificate WHERE id=?";

    private static final String DELETE_BONDING_BETWEEN_CERTIFICATE_AND_TAG_BY_BOTH_ID = "DELETE FROM m2m_gift_certificate_tag " +
            "WHERE gift_certificate_id=? AND tag_id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SQLGiftCertificateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiftCertificate insert(GiftCertificate giftCertificate) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(INSERT_GIFT_CERTIFICATE,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, giftCertificate.getName());
            statement.setString(2, giftCertificate.getDescription());
            statement.setBigDecimal(3, giftCertificate.getPrice());
            statement.setInt(4, giftCertificate.getDuration());
            statement.setTimestamp(5, Timestamp.valueOf(giftCertificate.getCreateDate()));
            statement.setTimestamp(6, Timestamp.valueOf(giftCertificate.getLastUpdateDate()));
            return statement;
        }, keyHolder);

        if (keyHolder.getKey()!=null){
            giftCertificate.setId(keyHolder.getKey().longValue());
        }
        return giftCertificate;
    }

    @Override
    public void update(GiftCertificate giftCertificate, long certificateId) {
            jdbcTemplate.update(UPDATE_GIFT_CERTIFICATION,  giftCertificate.getName(), giftCertificate.getDescription(),
                    giftCertificate.getPrice(), giftCertificate.getDuration(),
                    giftCertificate.getLastUpdateDate(), certificateId);
    }

    @Override
    public void delete(long certificateId) {
        jdbcTemplate.update(DELETE_GIFT_CERTIFICATION_BY_ID, certificateId);
    }

    @Override
    public List<GiftCertificate> read() {
        return jdbcTemplate.query(FIND_ALL_GIFT_CERTIFICATE, new GiftCertificateMapper());
    }

    @Override
    public GiftCertificate readById(long certificateId) {
        return jdbcTemplate.query(FIND_GIFT_CERTIFICATE_BY_ID, new GiftCertificateMapper(),
                new Object[]{certificateId}).stream().findAny().orElse(null);
    }

    @Override
    public List<GiftCertificate> findCertificatesByTagName(String tagName) {
        return jdbcTemplate.query(FIND_ALL_GIFT_CERTIFICATE_BY_TAG_NAME, new GiftCertificateMapper(),
                tagName);
    }

    @Override
    public List<GiftCertificate> findCertificatesByName(String certificateName) {
        return jdbcTemplate.query(FIND_ALL_GIFT_CERTIFICATE_BY_CERTIFICATE_NAME, new GiftCertificateMapper(),
                certificateName);
    }

    @Override
    public List<GiftCertificate> findCertificatesByDescription(String certificateDescription) {
        return jdbcTemplate.query(FIND_ALL_GIFT_CERTIFICATE_BY_DESCRIPTION, new GiftCertificateMapper(),
                certificateDescription);
    }

    @Override
    public void deleteCertificateTagById(long certificateId, long tagId) {
        jdbcTemplate.update(DELETE_BONDING_BETWEEN_CERTIFICATE_AND_TAG_BY_BOTH_ID, certificateId, tagId);
    }

}
