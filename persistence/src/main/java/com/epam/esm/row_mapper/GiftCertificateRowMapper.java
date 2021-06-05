package com.epam.esm.row_mapper;

import com.epam.esm.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName(rs.getString("name"));
        giftCertificate.setDescription(rs.getString("description"));
        giftCertificate.setDuration(rs.getInt("duration"));
        giftCertificate.setPrice(rs.getBigDecimal("price"));
        giftCertificate.setCreateDate(rs.getDate("create_date"));
        giftCertificate.setLastUpdateDate(rs.getDate("last_update_date"));
        return giftCertificate;
    }
}
