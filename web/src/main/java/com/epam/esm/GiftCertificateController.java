package com.epam.esm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Controller
@RequestMapping(value = "/giftCertificates")
public class GiftCertificateController {

    private final GiftCertificateDAO giftCertificateDAO;

    @Autowired
    public GiftCertificateController(@Qualifier("SQLGiftCertificateDAO") GiftCertificateDAO giftCertificateDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
    }
//++
    @GetMapping
    public String getAllGiftCertificates(Model model){
        List<GiftCertificate> giftCertificates = giftCertificateDAO.read();
        model.addAttribute("gift_certificates", giftCertificates);
        return "show_gift_certificates";
    }
//++
    @PostMapping
    public String createGiftCertificate(@ModelAttribute GiftCertificate giftCertificate){
        giftCertificateDAO.insert(giftCertificate);
        return "redirect:/giftCertificates/new";
    }
//++
    @GetMapping("/new")
    public String formForCreateGiftCertificate(){
        return "/form_create_gift_certificate";
    }
//++
    @GetMapping("/{id}/edit")
    public String formForEditGiftCertificate(@PathVariable int id, Model model){
        model.addAttribute("certificate", giftCertificateDAO.readById(id));
        return "/form_edit_gift_certificate";
    }
//++
    @GetMapping("/{id}")
    public String getGiftCertificate(@PathVariable int id, Model model){
        GiftCertificate giftCertificate = giftCertificateDAO.readById(id);
        model.addAttribute("certificate", giftCertificate);
        return "/show_gift_certificate";
    }

    //++
    @PatchMapping("/{id}")
    public String updateGiftCertificate(@ModelAttribute GiftCertificate giftCertificate, @PathVariable int id){
        giftCertificateDAO.update(giftCertificate, id);
        return "redirect:/gift_certificates/" + id;
    }
    //++
    @DeleteMapping("/{id}")
    public String deleteGiftCertificate(@PathVariable int id){
        giftCertificateDAO.delete(id);
        return "redirect:/giftCertificates";
    }
}
