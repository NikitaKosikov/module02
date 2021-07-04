package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.InvalidPrice;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.GiftCertificateValidator;
import com.epam.esm.validator.TagValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {

    @Mock
    private GiftCertificateDAO giftCertificateDAO;

    @Mock
    private GiftCertificateValidator giftCertificateValidator;

    private GiftCertificateService giftCertificateService;

    @Mock
    private static TagValidator tagValidator;
    @Mock
    private static GiftCertificateTagDao giftCertificateTagDao;
    @Mock
    private static TagService tagService;

    @BeforeEach
    public void initUseCase(){
        giftCertificateService = new GiftCertificateServiceImpl(giftCertificateDAO, giftCertificateValidator,
                tagValidator, giftCertificateTagDao, tagService);
    }

    @Test
    void insertGiftCertificatePositiveResult() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1L);
        giftCertificate.setPrice(new BigDecimal("33.33"));
        giftCertificate.setName("name");
        giftCertificate.setDescription("nameewq");
        giftCertificate.setDuration(31);
        giftCertificate.setCreateDate(LocalDateTime.of(2021,6, 24, 17,58));
        giftCertificate.setLastUpdateDate(LocalDateTime.of(2021,6, 24, 17,58));

        Mockito.when(giftCertificateDAO.insert(isA(GiftCertificate.class))).thenReturn(giftCertificate);

        GiftCertificate actualCertificate = giftCertificateDAO.insert(new GiftCertificate());

        assertThat(actualCertificate.getId()).isNotZero();
        assertEquals(actualCertificate, giftCertificate);
    }

    @Test
    void insertGiftCertificateThrowException() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1L);
        giftCertificate.setPrice(new BigDecimal("33.333"));
        giftCertificate.setName("name");
        giftCertificate.setDescription("nameewq");
        giftCertificate.setDuration(31);
        giftCertificate.setCreateDate(LocalDateTime.of(2021,6, 24, 17,58));
        giftCertificate.setLastUpdateDate(LocalDateTime.of(2021,6, 24, 17,58));

        doThrow(new InvalidPrice()).when(giftCertificateValidator)
                .validate(giftCertificate);

        assertThrows(InvalidPrice.class,
                () -> giftCertificateService.insert(giftCertificate, any(Tag.class)));
    }

    @Test
    void updateGiftCertificatePositiveResult() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1L);
        giftCertificate.setPrice(new BigDecimal("33.33"));
        giftCertificate.setName("name");
        giftCertificate.setDescription("nameewq");
        giftCertificate.setDuration(31);
        giftCertificate.setCreateDate(LocalDateTime.of(2021,6, 24, 17,58));
        giftCertificate.setLastUpdateDate(LocalDateTime.of(2021,6, 24, 17,58));


        long giftCertificateId = 0;
        doNothing().when(giftCertificateValidator).validate(giftCertificate);
        doNothing().when(giftCertificateDAO).update(any(GiftCertificate.class), anyLong());
        assertDoesNotThrow(() -> giftCertificateService.update(giftCertificate, giftCertificateId));
    }

    @Test
    void updateGiftCertificateThrowException() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1L);
        giftCertificate.setPrice(new BigDecimal("33.333"));
        giftCertificate.setName("name");
        giftCertificate.setDescription("nameewq");
        giftCertificate.setDuration(31);
        giftCertificate.setCreateDate(LocalDateTime.of(2021,6, 24, 17,58));
        giftCertificate.setLastUpdateDate(LocalDateTime.of(2021,6, 24, 17,58));

        doThrow(new InvalidPrice()).when(giftCertificateValidator)
                .validate(giftCertificate);

        assertThrows(InvalidPrice.class,
                () -> giftCertificateService.update(giftCertificate, anyInt()));
    }

    @Test
    void deleteGiftCertificatePositiveResult() {
        final long id = 1;
        doNothing().when(giftCertificateTagDao).deleteConnectionByGiftCertificateId(anyLong());
        doNothing().when(giftCertificateDAO).delete(anyLong());
        assertDoesNotThrow(() -> giftCertificateService.delete(id));
    }

    @Test
    void readGiftCertificatesPositiveResult() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificates.add(giftCertificate);

        when(giftCertificateDAO.read()).thenReturn(giftCertificates);
        List<GiftCertificate> actualGiftCertificateList = giftCertificateService.read();

        assertThat(actualGiftCertificateList.size()).isGreaterThan(0);
    }

    @Test
    void readGiftCertificateByIdPositiveResult() {
        when(giftCertificateDAO.readById(1)).thenReturn(new GiftCertificate());
        GiftCertificate foundGiftCertificate = giftCertificateService.readById(1);
        assertNotNull(foundGiftCertificate);
    }

    @Test
    void addTag() {
        final long certificateId = 1;
        Tag tag = new Tag();
        tag.setName("das");
        doNothing().when(tagValidator).validateName("das");
        when(tagService.findTagByName(tag.getName())).thenReturn(tag);
        doNothing().when(giftCertificateTagDao).createConnection(anyLong(), anyLong());

        assertDoesNotThrow(() -> giftCertificateService.addTag(certificateId, tag));
    }

    @Test
    void findCertificatesByTagName() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificates.add(giftCertificate);
        when(giftCertificateDAO.findCertificatesByTagName("salon")).thenReturn(giftCertificates);

        List<GiftCertificate> readerGiftCertificate = giftCertificateService.findCertificatesByTagName("salon");

        assertThat(readerGiftCertificate).isNotEmpty();
    }

    @Test
    void findCertificatesByName() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName("Beauty salon");
        giftCertificates.add(giftCertificate);
        when(giftCertificateDAO.findCertificatesByName("Beauty salon")).thenReturn(giftCertificates);

        List<GiftCertificate> readerGiftCertificate = giftCertificateService.findCertificatesByName("Beauty salon");

        assertThat(readerGiftCertificate).isNotEmpty();
    }

    @Test
    void findCertificatesByDescription() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setDescription("Salon best in the city");
        giftCertificates.add(giftCertificate);
        when(giftCertificateDAO.findCertificatesByDescription("best in the city"))
                .thenReturn(giftCertificates);

        List<GiftCertificate> readerGiftCertificate =
                giftCertificateService.findCertificatesByDescription("best in the city");

        assertThat(readerGiftCertificate).isNotEmpty();
    }

    @Test
    void updateCertificateTagById() {

        final long certificateId = 1;
        final long tagId = 1;
        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("testName");
        doNothing().when(tagValidator).validateName(tag.getName());
        when(tagService.findTagByName(tag.getName())).thenReturn(tag);
        doNothing().when(giftCertificateTagDao).updateCertificateTag(anyLong(), anyLong(), anyLong());
        assertDoesNotThrow(() -> giftCertificateService.updateCertificateTagById(certificateId, tagId, "testName"));
    }

    @Test
    void deleteCertificateTagById() {
        doNothing().when(giftCertificateDAO).deleteCertificateTagById(anyLong(), anyLong());
        giftCertificateDAO.deleteCertificateTagById(anyInt(), anyInt());
        verify(giftCertificateDAO, times(1))
                .deleteCertificateTagById(0, 0);
    }
}