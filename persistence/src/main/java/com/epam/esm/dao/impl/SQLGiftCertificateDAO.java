package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.Tag;
import com.epam.esm.row_mapper.GiftCertificateRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SQLGiftCertificateDAO implements GiftCertificateDAO {

    private static final String FIND_ALL_GIFT_CERTIFICATE = "SELECT * FROM gift_certificate";

    private static final String FIND_ALL_GIFT_CERTIFICATE_BY_TAG_NAME = "SELECT * FROM m2m_gift_certificate_tag " +
            "JOIN gift_certificate gc on gc.id = m2m_gift_certificate_tag.gift_certificate_id " +
            "JOIN tag t on t.id = m2m_gift_certificate_tag.tag_id " +
            "WHERE t.name=? OR gc.name Like ? OR gc.description LIKE ? " +
            "ORDER BY gc.name";

    private static final String FIND_TAG_BY_NAME = "SELECT * FROM tag where name=?";

    private static final String FIND_GIFT_CERTIFICATE_BY_ID = "SELECT * FROM gift_certificate WHERE id =?";

    private static final String INSERT_GIFT_CERTIFICATE = "INSERT INTO gift_certificate " +
            "(name, description, price, duration, create_date, last_update_date) " +
            "VALUES (?,?,?,?,?,?)";

    private static final String BLIND_GIFT_CERTIFICATE_WITH_TAG = "INSERT INTO m2m_gift_certificate_tag " +
            "(gift_certificate_id, tag_id) VALUES (?,?)";

    private static final String INSERT_TAG = "INSERT INTO tag " +
            "(name) VALUES (?)";


    private static final String UPDATE_GIFT_CERTIFICATION = "UPDATE gift_certificate SET " +
            "name = ?, description=?, price=?, duration=?, create_date=?, last_update_date=? " +
            "WHERE id=?";

    private static final String DELETE_GIFT_CERTIFICATION_BY_ID = "DELETE FROM gift_certificate WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public SQLGiftCertificateDAO(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
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
    public void update(GiftCertificate giftCertificate, int id, String tagName) {
        String name = giftCertificate.getName();
        String description = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        int duration = giftCertificate.getDuration();
        Date createDate = giftCertificate.getCreateDate();
        Date lastUpdateDate = giftCertificate.getLastUpdateDate();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "#" + tagName);

        if (!isExistTag(tagName)){
            simpleJdbcInsert.executeAndReturnKey(parameters);
        }

        jdbcTemplate.update(UPDATE_GIFT_CERTIFICATION, name, description, price, duration, createDate, lastUpdateDate, id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_GIFT_CERTIFICATION_BY_ID, id);
    }

    @Override
    public List<GiftCertificate> read() {
        return jdbcTemplate.query(FIND_ALL_GIFT_CERTIFICATE, new GiftCertificateRowMapper());
    }

    @Override
    public GiftCertificate readById(int id) {
        return jdbcTemplate.query(FIND_GIFT_CERTIFICATE_BY_ID,new Object[]{id},
                new GiftCertificateRowMapper()).stream().findAny().orElse(null);
    }

    @Override
    public void addTag(int id, Tag tag) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "#" + tag.getName());
        Tag dbTag = jdbcTemplate.query(FIND_TAG_BY_NAME, new Object[]{tag.getName()}, new BeanPropertyRowMapper<>(Tag.class))
                .stream().findAny().orElse(null);

        Number tagId;
        if (dbTag == null) {
            tagId = simpleJdbcInsert.executeAndReturnKey(parameters);
        }else {
            tagId = dbTag.getId();
        }
        jdbcTemplate.update(BLIND_GIFT_CERTIFICATE_WITH_TAG, id, tagId);

    }

    @Override
    public List<GiftCertificate> findCertificatesByTagName(String tagName) {
        return jdbcTemplate.query(FIND_ALL_GIFT_CERTIFICATE_BY_TAG_NAME,
                new Object[]{tagName,'%' + tagName + '%','%' + tagName + '%'}, new GiftCertificateRowMapper());
    }

    private boolean isExistTag(String tagName){
        Tag dbTag = jdbcTemplate.query(FIND_TAG_BY_NAME, new Object[]{tagName}, new BeanPropertyRowMapper<>(Tag.class))
                .stream().findAny().orElse(null);
        return dbTag != null;
    }
}
