package com.aggy.restfulwebservices.hw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;

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

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world-in")
    public String getHelloWorldIn(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message",null, locale);
    }

}