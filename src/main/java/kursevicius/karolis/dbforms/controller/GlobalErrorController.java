package kursevicius.karolis.dbforms.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@ControllerAdvice
public class GlobalErrorController implements ErrorController {
    private static final String PATH = "/error";

    @ExceptionHandler({Exception.class})
    public ModelAndView exception(Exception e) {
        return new ModelAndView("error/404", "errorMessage", e.getMessage());
    }

    @RequestMapping(PATH)
    public void error(HttpServletResponse response) throws IOException {
        switch (response.getStatus()) {
            case 404:
                response.sendRedirect("/404");
                break;
            case 401:
                response.sendRedirect("/404");
                break;
            case 403:
                response.sendRedirect("/404");
                break;
            default:
                response.sendRedirect("/404");
        }
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
