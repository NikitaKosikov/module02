package com.epam.esm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TagController {

    @GetMapping("/tags")
    public String getAllTags(){
        return "";
    }

    @PostMapping("/tags")
    public String createTag(){
        return "";
    }

    @GetMapping("/tags/new")
    public String formForCreateTag(){
        return "";
    }

    @GetMapping("/tags/{id}/edit")
    public String formForEditTag(){
        return "";
    }

    @GetMapping("/tags/{id}")
    public String getTag(){
        return "";
    }

    @PatchMapping("/tags/{id}")
    public String updateTag(){
        return "";
    }

    @DeleteMapping("/tags/{id}")
    public String deleteTag(){
        return "";
    }
}
