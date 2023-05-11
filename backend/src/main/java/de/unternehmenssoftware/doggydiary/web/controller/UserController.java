package de.unternehmenssoftware.doggydiary.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/api/v1")
public class UserController {

    @GetMapping(path = "/test")
    public String testReturn() {
        return "test";
    }

}