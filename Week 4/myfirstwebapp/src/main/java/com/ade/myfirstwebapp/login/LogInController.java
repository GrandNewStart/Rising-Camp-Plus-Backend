package com.ade.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogInController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "login")
    public String goToLogInPage(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        logger.debug("Request param is {}", name);
        return "login";
    }

}
