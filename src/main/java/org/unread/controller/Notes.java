package org.unread.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Notes {

    @GetMapping("/{userName}/notes")
    public String getNotes(@PathVariable String userName) {
        return userName + " : notes";
    }
}
