package com.aggy.restfulwebservices.hw;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String getHelloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean  getHelloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/path-var/{param}")
    public HelloWorldBean getHelloWorldBeanWithParam(@PathVariable String param) {
        return new HelloWorldBean(String.format("Hello World, %s", param));
    }
}