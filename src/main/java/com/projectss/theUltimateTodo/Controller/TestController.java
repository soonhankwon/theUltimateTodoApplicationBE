package com.projectss.theUltimateTodo.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    
    @GetMapping("/")
    public String main(){
        return "hello world!";
    }
    @GetMapping("/test")
    public String test(@RequestBody String body) {
        log.info("TestController.test() called");
        return "Hello World!";
    }
    @GetMapping("/test2")
    public String test2() {
        log.info("TestController.test2() called");
        return "Hello World!";
    }
}
