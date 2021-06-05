package com.epam.esm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TagController {

    private final TagDAO tagDAO;

    @Autowired
    public TagController(@Qualifier("SQLTagDAO") TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @GetMapping("/tags")
    public String getAllTags(Model model){
        List<Tag> tags = tagDAO.read();
        model.addAttribute(tags);
        return "";
    }

    @PostMapping("/tags")
    public String createTag(@ModelAttribute Tag tag){
        tagDAO.insert(tag);
        return "";
    }

    @GetMapping("/tags/new")
    public String formForCreateTag(){
        return "";
    }

    @GetMapping("/tags/{id}/edit")
    public String formForEditTag(@PathVariable int id){
        return "";
    }

    @GetMapping("/tags/{id}")
    public String getTag(@PathVariable int id, Model model){
        Tag tag = tagDAO.readById(id);
        model.addAttribute("tag", tag);
        return "";
    }

    @PatchMapping("/tags/{id}")
    public String updateTag(@ModelAttribute Tag tag, @PathVariable int id){
        tagDAO.update(tag, id);
        return "";
    }

    @DeleteMapping("/tags/{id}")
    public String deleteTag(@PathVariable int id){
        tagDAO.delete();
        return "";
    }
}
