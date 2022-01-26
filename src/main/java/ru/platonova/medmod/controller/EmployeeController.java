package ru.platonova.medmod.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeController {

    @GetMapping("/all")
    public String allAccess() {
        return "public API";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('physician') or hasRole('nurse')")
    public String userAccess() {
        return "user API";
    }
}
