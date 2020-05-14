package com.example.threadpool.controller;

import com.example.threadpool.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/")
    public String submit() {
        log.info("start submit");
        asyncService.executeAsync();
        log.info("end submit");
        return "success";
    }
}
