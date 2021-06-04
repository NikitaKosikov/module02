package com.epam.esm.impl;

import com.epam.esm.GiftCertificate;
import com.epam.esm.GiftCertificateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class SQLGiftCertificateDAO implements GiftCertificateDAO {

    private static final String FIND_ALL_GIFT_CERTIFICATE = "SELECT * FROM gift_certificate";

    private static final String FIND_GIFT_CERTIFICATE_BY_ID = "SELECT * FROM gift_certificate WHERE id =?";

    private static final String INSERT_GIFT_CERTIFICATE = "INSERT INTO gift_certificate " +
            "(name, description, price, duration, create_date, last_update_date) " +
            "VALUES (?,?,?,?,?,?)";

    private static final String UPDATE_GIFT_CERTIFICATION = "UPDATE gift_certificate SET " +
            "name = ?, description=?, price=?, duration=?, create_date=?, last_update_date=? " +
            "WHERE id=?";

    private static final String DELETE_GIFT_CERTIFICATION_BY_ID = "DELETE FROM gift_certificate WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SQLGiftCertificateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(GiftCertificate giftCertificate) {
        String name = giftCertificate.getName();
        String description = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        int duration = giftCertificate.getDuration();
        Date createDate = giftCertificate.getCreateDate();
        Date lastUpdateDate = giftCertificate.getLastUpdateDate();

        jdbcTemplate.update(INSERT_GIFT_CERTIFICATE, name, description, price, duration, createDate, lastUpdateDate);
    }

    @Override
    public void update(GiftCertificate giftCertificate, int id) {
        String name = giftCertificate.getName();
        String description = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        int duration = giftCertificate.getDuration();
        Date createDate = giftCertificate.getCreateDate();
        Date lastUpdateDate = giftCertificate.getLastUpdateDate();

        jdbcTemplate.update(UPDATE_GIFT_CERTIFICATION, name, description, price, duration, createDate, lastUpdateDate, id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_GIFT_CERTIFICATION_BY_ID, id);
    }

    @Override
    public List<GiftCertificate> read() {
        return jdbcTemplate.queryForList(FIND_ALL_GIFT_CERTIFICATE, GiftCertificate.class);
    }

    @Override
    public GiftCertificate readById(int id) {
        return jdbcTemplate.queryForObject(FIND_GIFT_CERTIFICATE_BY_ID,new Object[]{id}, new int[]{id},  GiftCertificate.class);
    }
}
