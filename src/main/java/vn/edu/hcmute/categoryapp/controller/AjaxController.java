package vn.edu.hcmute.categoryapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AjaxController {

    @GetMapping("/")
    public String home() {
        return "redirect:/ajax/products";
    }

    @GetMapping("/ajax/products")
    public String products() {
        return "ajax/products";
    }

    @GetMapping("/ajax/categories")
    public String categories() {
        return "ajax/categories";
    }
}