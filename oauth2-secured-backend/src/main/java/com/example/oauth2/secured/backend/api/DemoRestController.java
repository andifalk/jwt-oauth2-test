package com.example.oauth2.secured.backend.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

    @RequestMapping(path = "/restricted")
    public String sayHelloProtected() {
        return "Access to protected resource granted";
    }

    @RequestMapping(path = {"/", "/unrestricted"})
    public String sayHelloUnProtected() {
        return "Unprotected resource";
    }

}
