package com.epam.esm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public String getAllTags(Model model){
        List<Tag> tags = tagService.read();
        model.addAttribute("tags", tags);
        return "show_tags";
    }

    @PostMapping
    public String createTag(@ModelAttribute Tag tag){
        tagService.insert(tag);
        return "redirect:/tags";
    }

    @GetMapping("/new")
    public String formForCreateTag(){
        return "form_create_tag";
    }

    @GetMapping("/{id}/edit")
    public String formForEditTag(@PathVariable int id, Model model){
        model.addAttribute("tag", tagService.readById(id));
        return "form_edit_tag";
    }

    @GetMapping("/{id}")
    public String getTag(@PathVariable int id, Model model){
        Tag tag = tagService.readById(id);
        model.addAttribute("tag", tag);
        return "show_tag";
    }

    @PatchMapping("/{id}")
    public String updateTag(@ModelAttribute Tag tag, @PathVariable int id){
        tagService.update(tag, id);
        return "redirect:/tags/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable int id){
        tagService.delete(id);
        return "redirect:/tags";
    }
}
