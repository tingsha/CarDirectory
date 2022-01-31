package com.naname.carddirectory.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping(value = "/")
public class HomeController {

//    private final FieldValidator fieldValidator;
//
//    @Autowired
//    public HomeController(FieldValidator fieldValidator) {
//        this.fieldValidator = fieldValidator;
//    }

    @GetMapping
    public String redirect(){
        return "redirect:/cars?page=1";
    }

}
