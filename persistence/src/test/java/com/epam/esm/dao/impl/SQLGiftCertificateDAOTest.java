package com.epam.esm.dao.impl;

import com.epam.esm.config.TestConfiguration;
import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificate;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ComponentScan("com.epam.esm")
public class SQLGiftCertificateDAOTest{

    private static GiftCertificate giftCertificate;
    private static GiftCertificate giftCertificate1;
    private static GiftCertificate giftCertificate2;

    private final GiftCertificateDAO giftCertificateDAO;

    @Autowired
    public SQLGiftCertificateDAOTest(GiftCertificateDAO giftCertificateDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
    }


    @BeforeAll
    public static void setUp() {
        giftCertificate = new GiftCertificate(1L, "Cinema", "Best cinema in the city", new BigDecimal(100), 5,
                LocalDateTime.of(2020, 12, 12, 12, 0, 0), LocalDateTime.of(2020, 12, 13, 12, 0, 0));
        giftCertificate1 = new GiftCertificate(4L, "Cinema", "Best cinema in the city", new BigDecimal(100), 5,
                LocalDateTime.of(2020, 12, 12, 12, 0, 0), LocalDateTime.of(2020, 12, 13, 12, 0, 0));
        giftCertificate2 = new GiftCertificate(3L, "Horse ride", "Horseback ride for lovers - a Hollywood-style date",
                new BigDecimal("500.00"), 2, LocalDateTime.of(2017, 05, 22, 12, 46, 31),
                LocalDateTime.of(2020, 03, 20, 16, 34, 49));
    }

    @Test
    public void createPositiveTest() {
        GiftCertificate actual = giftCertificateDAO.insert(giftCertificate);
        assertEquals(giftCertificate1, actual);
    }

    @Test
    public void findByNamePositiveTest() {
        final int expectedNumberGiftCertificates = 1;
        List<GiftCertificate> actual = giftCertificateDAO.findCertificatesByName("Horse ride");
        assertEquals(expectedNumberGiftCertificates, actual.size());
    }

    @Test
    public void findByDescriptionNegativeTest() {
        List<GiftCertificate> actual = giftCertificateDAO.findCertificatesByDescription("Best cinema");
        assertFalse(actual.isEmpty());
    }

    @Test
    public void findCertificateByIdPositiveTest() {
        final long id = 3;
        GiftCertificate actual = giftCertificateDAO.readById(id);
        assertEquals(giftCertificate2, actual);
    }

    @Test
    public void findByIdNegativeTest() {
        final long id = 10;
        GiftCertificate actual = giftCertificateDAO.readById(id);
        assertThat(actual).isNull();
    }

    @Test
    public void deletePositiveTest() {
        final long id = 2;
        giftCertificateDAO.delete(id);
        GiftCertificate actual = giftCertificateDAO.readById(id);
        assertThat(actual).isNull();
    }
}