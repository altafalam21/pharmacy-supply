package com.cognizant.portal.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    public MyErrorController() {}

    @GetMapping("/error")
    public String getErrorPage(HttpServletRequest request) {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "error";
    }

}