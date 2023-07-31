package com.ade.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogInController {

    @RequestMapping(value = "login")
    public String goToLogInPage(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        System.out.println(name);
        return "login";
    }

}
