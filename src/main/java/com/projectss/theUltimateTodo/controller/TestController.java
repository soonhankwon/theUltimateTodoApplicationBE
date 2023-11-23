package com.projectss.theUltimateTodo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    
    @GetMapping("/")
    public String main(){
        return "hello world!";
    }

    @GetMapping("/test")
    public String test() {
        log.info("TestController.test2() called");
        return "bye World!";
    }
    @GetMapping("/.well-known/acme-challenge/IR9LWWjenpX23LyKi8XZ6-JaVKsV_quOMdHoWBsGV3w")
    public String cert(){
        return "IR9LWWjenpX23LyKi8XZ6-JaVKsV_quOMdHoWBsGV3w.X3xz659Vj8PmfKVSv2XnKSc9hvA9Nu2gmOoZdeKDGUc";
    }

}
