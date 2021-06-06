package com.epam.esm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping(value = "/giftCertificates")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    public String getAllGiftCertificates(Model model){
        List<GiftCertificate> giftCertificates = giftCertificateService.read();
        model.addAttribute("gift_certificates", giftCertificates);
        return "show_gift_certificates";
    }

    @PostMapping
    public String createGiftCertificate(@ModelAttribute GiftCertificate giftCertificate,
                                        @ModelAttribute Tag tag) throws ParseException {
        DateFormat dateFormat = getDateTimeIso8610();
        String nowDateTime = dateFormat.format(new Date());

        giftCertificate.setCreateDate(dateFormat.parse(nowDateTime));
        giftCertificate.setLastUpdateDate(dateFormat.parse(nowDateTime));

        giftCertificateService.insert(giftCertificate);
        return "redirect:/giftCertificates/new";
    }

    @GetMapping("/new")
    public String formForCreateGiftCertificate(){
        return "/form_create_gift_certificate";
    }

    @GetMapping("/{id}/edit")
    public String formForEditGiftCertificate(@PathVariable int id, Model model){
        model.addAttribute("certificate", giftCertificateService.readById(id));
        return "/form_edit_gift_certificate";
    }

    @GetMapping("/{id}")
    public String getGiftCertificate(@PathVariable int id, Model model){
        GiftCertificate giftCertificate = giftCertificateService.readById(id);
        model.addAttribute("certificate", giftCertificate);
        return "/show_gift_certificate";
    }


    @PatchMapping("/{id}")
    public String updateGiftCertificate(@ModelAttribute GiftCertificate giftCertificate, @PathVariable int id) throws ParseException {
        DateFormat dateFormat = getDateTimeIso8610();
        String nowDateTime = dateFormat.format(new Date());
        giftCertificate.setLastUpdateDate(dateFormat.parse(nowDateTime));
        giftCertificateService.update(giftCertificate, id);
        return "redirect:/gift_certificates/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteGiftCertificate(@PathVariable int id){
        giftCertificateService.delete(id);
        return "redirect:/giftCertificates";
    }

    @PostMapping("/{id}/tags")
    public String addTagToCertificate(@PathVariable int id,@ModelAttribute Tag tag){
        giftCertificateService.addTag(id, tag);
        return "redirect:/giftCertificates/" + id;
    }

    private DateFormat getDateTimeIso8610(){
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        return df;
    }
}
