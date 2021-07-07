package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import com.in28minutes.rest.webservices.restfulwebservices.helloworld.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    //GET
    //URI - /hello-world
    //method - "Hello World"
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
