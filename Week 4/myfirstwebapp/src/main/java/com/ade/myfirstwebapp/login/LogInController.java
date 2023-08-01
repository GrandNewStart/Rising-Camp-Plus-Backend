package com.ade.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LogInController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final AuthenticationService authenticationService;

    public LogInController(AuthenticationService authenticationService) {
        super();
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goToLogInPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap map) {
        if (this.authenticationService.authenticate(name, password)) {
            map.put("name", name);
            map.put("password", password);
            return "welcome";
        } else {
            map.put("errorMessage", "Invalid credentials. Please try again.");
            return "login";
        }
    }

}
