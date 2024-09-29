package com.github.ubercommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TShirtGeneratorController {

    @GetMapping({"", "/","/preview"})
    public String preview(Model model) {
        return "preview";
    }

}
