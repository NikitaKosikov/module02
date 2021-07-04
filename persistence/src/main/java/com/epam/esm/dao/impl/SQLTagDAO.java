package com.epam.esm.dao.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.dao.TagDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Component
public class SQLTagDAO implements TagDAO {

    private static final String INSERT_TAG = "INSERT INTO tag (name) VALUES (?)";

    private static final String UPDATE_TAG = "UPDATE tag SET name=? WHERE id=?";

    private static final String DELETE_TAG_BY_ID = "DELETE FROM tag WHERE id=?";

    private static final String FIND_ALL_TAG = "SELECT * FROM tag";

    private static final String FIND_TAG_BY_NAME = "SELECT name, id FROM tag WHERE name=?";

    private static final String FIND_TAG_BY_ID = "SELECT * FROM tag WHERE id=?";

    private static final String FIND_TAG_BY_CERTIFICATE_ID = "SELECT t.name, t.id FROM m2m_gift_certificate_tag " +
            "JOIN tag t on t.id = m2m_gift_certificate_tag.tag_id WHERE gift_certificate_id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SQLTagDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag insert(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Tag dbTag = findTagByName(tag.getName());
        if (dbTag==null){
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(INSERT_TAG,
                        Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, tag.getName());
                return statement;
            }, keyHolder);
        }else {
            return dbTag;
        }

        if (keyHolder.getKey()!=null){
            tag.setId(keyHolder.getKey().longValue());
        }
        return tag;
    }

    @Override
    public Tag findTagByName(String tagName) {
        return jdbcTemplate.query(FIND_TAG_BY_NAME, new BeanPropertyRowMapper<>(Tag.class), new Object[]{tagName})
                .stream().findAny().orElse(null);
    }

    @Override
    public void update(Tag tag, long id) {
        Tag dbTag = jdbcTemplate.query(FIND_TAG_BY_NAME, new BeanPropertyRowMapper<>(Tag.class),
                new Object[]{tag.getName()}).stream().findAny().orElse(null);
        if (dbTag==null){
            jdbcTemplate.update(UPDATE_TAG, tag.getName(), id);
        }
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(DELETE_TAG_BY_ID, id);
    }

    @Override
    public List<Tag> read() {
        return jdbcTemplate.query(FIND_ALL_TAG, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag readById(long id) {
        return jdbcTemplate.query(FIND_TAG_BY_ID,
                new BeanPropertyRowMapper<>(Tag.class), new Object[]{id}).stream().findAny().orElse(null);
    }

    @Override
    public List<Tag> readByCertificateId(long id) {
        return jdbcTemplate.query(FIND_TAG_BY_CERTIFICATE_ID,
                new BeanPropertyRowMapper<>(Tag.class), id);
    }
}
