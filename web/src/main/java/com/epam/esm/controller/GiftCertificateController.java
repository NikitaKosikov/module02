package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/giftCertificates")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    private final TagService tagService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService, TagService tagService) {
        this.giftCertificateService = giftCertificateService;
        this.tagService = tagService;
    }

    @GetMapping
    public List<GiftCertificate> getAllGiftCertificates(){
        return giftCertificateService.read();
    }

    @PostMapping
    public GiftCertificate createGiftCertificate(@RequestBody GiftCertificate giftCertificate,
                                        @RequestParam("tagName") String tagName){
        Tag tag = new Tag(tagName);
        return giftCertificateService.insert(giftCertificate, tag);
    }

    @GetMapping("/{certificateId}")
    public GiftCertificate getGiftCertificate(@PathVariable int certificateId){
        return giftCertificateService.readById(certificateId);
    }

    @PatchMapping("/{certificateId}")
    public void updateGiftCertificate(@RequestBody GiftCertificate giftCertificate,
                                        @PathVariable int certificateId){
        giftCertificateService.update(giftCertificate, certificateId);
    }

    @DeleteMapping("/{certificateId}")
    public void deleteGiftCertificate(@PathVariable int certificateId){
        giftCertificateService.delete(certificateId);
    }

    @GetMapping("/{certificateId}/tags")
    public List<Tag> getTagsOfCertificate(@PathVariable int certificateId){
      return tagService.readByCertificateId(certificateId);
    }

    @PostMapping("/{certificateId}/tags")
    public void addTagToCertificate(@PathVariable int certificateId,@RequestBody Tag tag){
        giftCertificateService.addTag(certificateId, tag);
    }

    @GetMapping("/tagName")
    public List<GiftCertificate> findCertificatesByTagName(@RequestParam("tag_name") String tagName) {
        return giftCertificateService.findCertificatesByTagName(tagName);
    }

    @GetMapping("/name")
    public List<GiftCertificate>  findCertificatesByName(@RequestParam("certificate_name") String certificateName) {
        return giftCertificateService.findCertificatesByName(certificateName);
    }

    @GetMapping("/description")
    public List<GiftCertificate> findCertificatesByDescription(@RequestParam("certificate_description") String certificateDescription){
        return  giftCertificateService.findCertificatesByDescription(certificateDescription);
    }

    @PatchMapping("/{certificateId}/tags/{tagId}")
    public void updateTag(@PathVariable int certificateId, @PathVariable int tagId, @RequestParam String tagName) {
        giftCertificateService.updateCertificateTagById(certificateId,tagId, tagName);
    }

    @DeleteMapping("/{certificateId}/tags/{tagId}")
    public void deleteTag(@PathVariable int certificateId, @PathVariable int tagId){
        giftCertificateService.deleteCertificateTagById(certificateId,tagId);
    }
}
