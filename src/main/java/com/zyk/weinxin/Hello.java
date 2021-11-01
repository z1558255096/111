package com.zyk.weinxin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Hello {

        @RequestMapping("/")
        public String hello(HttpServletRequest request){
            request.setAttribute("hello", "hello jsp");
            System.out.println("hello jsp");
            return "zyk";

        }

}
