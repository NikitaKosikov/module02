package com.epam.esm.row_mapper;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftCertificateMapper implements RowMapper<GiftCertificate> {

    private static final String CERTIFICATE_ID = "id";
    private static final String CERTIFICATE_NAME = "name";
    private static final String CERTIFICATE_DESCRIPTION = "description";
    private static final String CERTIFICATE_PRICE = "price";
    private static final String CERTIFICATE_DURATION = "duration";
    private static final String CERTIFICATE_CREATE_DATE = "create_date";
    private static final String CERTIFICATE_LAST_UPDATE_DATE = "last_update_date";

    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(rs.getLong(CERTIFICATE_ID));
        giftCertificate.setName(rs.getString(CERTIFICATE_NAME));
        giftCertificate.setDescription(rs.getString(CERTIFICATE_DESCRIPTION));
        giftCertificate.setDuration(rs.getInt(CERTIFICATE_DURATION));
        giftCertificate.setPrice(rs.getBigDecimal(CERTIFICATE_PRICE));
        giftCertificate.setCreateDate(rs.getTimestamp(CERTIFICATE_CREATE_DATE).toLocalDateTime());
        giftCertificate.setLastUpdateDate(rs.getTimestamp(CERTIFICATE_LAST_UPDATE_DATE).toLocalDateTime());
        return giftCertificate;
    }
}
