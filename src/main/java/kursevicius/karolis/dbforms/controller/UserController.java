package kursevicius.karolis.dbforms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @GetMapping("/login")
    public String getLoginPage() {
        return "user/login";
    }

    @GetMapping("/login-failure")
    public ModelAndView getLoginPageWithFailure() {
        return new ModelAndView("user/login", "hasError", true);
    }
}
