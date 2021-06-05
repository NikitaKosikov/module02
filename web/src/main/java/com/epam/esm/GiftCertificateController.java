package com.epam.esm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GiftCertificateController {

    private final GiftCertificateDAO giftCertificateDAO;

    @Autowired
    public GiftCertificateController(@Qualifier("SQLGiftCertificateDAO") GiftCertificateDAO giftCertificateDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
    }

    @GetMapping("giftCertificates")
    public String getAllGiftCertificates(Model model){
        List<GiftCertificate> giftCertificates = giftCertificateDAO.read();
        model.addAttribute(giftCertificates);
        return "show_gift_certificates";
    }

    @PostMapping("/giftCertificates")
    public String createGiftCertificate(@ModelAttribute GiftCertificate giftCertificate){
        giftCertificateDAO.insert(giftCertificate);
        return "show_gift_certificates";
    }

    @GetMapping("/giftCertificates/new")
    public String formForCreateGiftCertificate(){
        return "form_create_gift_certificate";
    }

    @GetMapping("/giftCertificates/{id}/edit")
    public String formForEditGiftCertificate(@PathVariable String id, Model model){
        model.addAttribute("gift_certificate_id", id);
        return "form_edit_gift_certificate";
    }

    @GetMapping("/giftCertificates/{id}")
    public String getGiftCertificate(@PathVariable int id, Model model){
        GiftCertificate giftCertificate = giftCertificateDAO.readById(id);
        model.addAttribute(giftCertificate);
        return "show_gift_certificate";
    }

    @PatchMapping("/giftCertificates/{id}")
    public String updateGiftCertificate(@ModelAttribute GiftCertificate giftCertificate, @PathVariable int id){
        giftCertificateDAO.update(giftCertificate, id);
        return "show_gift_certificates";
    }

    @DeleteMapping("/giftCertificates/{id}")
    public String deleteGiftCertificate(@PathVariable int id){
        giftCertificateDAO.delete(id);
        return "show_gift_certificates";
    }
}
