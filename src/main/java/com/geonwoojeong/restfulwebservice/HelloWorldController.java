package com.geonwoojeong.restfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @GetMapping("/hello-world")
  public String helloWorld() {
    return "Hello World";
  }

  @GetMapping("/hello-world-bean/path-variable/{name}")
  public HelloWorldBean helloWorldBean(@PathVariable String name) {
    return new HelloWorldBean(String.format("Hello World, %s", name));
  }
}
