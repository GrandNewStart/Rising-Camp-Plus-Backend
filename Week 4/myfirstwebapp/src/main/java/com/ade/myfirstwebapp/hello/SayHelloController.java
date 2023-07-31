package com.ade.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello!";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHTML() {
        StringBuffer bf = new StringBuffer();
        bf.append("<html>");
        bf.append("<head>");
        bf.append("<title>My First HTML Page</title>");
        bf.append("</head>");
        bf.append("<body>");
        bf.append("My First HTML body");
        bf.append("</body>");
        bf.append("</html>");
        return bf.toString();
    }

    @RequestMapping("say-hello-jsp")
    public String sayHelloJSP() {
        return "hello";
    }

}
