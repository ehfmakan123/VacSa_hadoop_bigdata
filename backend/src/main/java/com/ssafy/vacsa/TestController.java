package com.ssafy.vacsa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/hello")
    public String sayHello() {
        return "<h1>GetMapping 테스트용</h1>";
    }
}
