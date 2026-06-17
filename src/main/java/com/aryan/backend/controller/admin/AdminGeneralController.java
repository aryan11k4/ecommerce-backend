package com.aryan.backend.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminGeneralController {

    @GetMapping("/getCSRF")
    public CsrfToken getToken(HttpServletRequest sr){
        return (CsrfToken) sr.getAttribute("_csrf");
    }
}
