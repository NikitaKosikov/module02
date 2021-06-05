package com.epam.esm.impl;

import com.epam.esm.Tag;
import com.epam.esm.TagDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SQLTagDAO implements TagDAO {

    private static final String INSERT_TAG = "INSERT INTO tag (name) VALUES (?)";
    private static final String UPDATE_TAG = "UPDATE tag SET name=? WHERE id=?";
    private static final String DELETE_TAG_BY_ID = "DELETE FROM tag WHERE id=?";
    private static final String FIND_ALL_TAG = "DELETE FROM tag WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SQLTagDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(String name) {
        jdbcTemplate.update(INSERT_TAG, name);
    }

    @Override
    public void update(String name, int id) {
        jdbcTemplate.update(UPDATE_TAG, name, id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_TAG_BY_ID, id);
    }

    @Override
    public List<Tag> read() {
        return jdbcTemplate.query(FIND_ALL_TAG, new BeanPropertyRowMapper<>(Tag.class));
    }
}
