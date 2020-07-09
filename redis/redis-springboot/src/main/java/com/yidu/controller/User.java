package com.yidu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author long
 * @date 2020-07-09 8:21
 */
@Controller
@RequestMapping("/user")
public class User {

    @GetMapping("/hello.do")
    public String hello(String userName,String userPass){

        System.out.println(userName+":"+userPass);



        return "hello world!";
    }
}
