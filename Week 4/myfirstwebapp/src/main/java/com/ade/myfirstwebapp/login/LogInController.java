package com.ade.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogInController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goToLogInPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap map) {
        map.put("name", name);
        map.put("password", password);
        return "welcome";
    }

}
