package chora.auth.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlugController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Auth server is running";
    }

    @RequestMapping(".well-known/**")
    public void wellKnown(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @GetMapping("/login")
    public String loginRedirect() {
        return "forward:/login/index.html";
    }
}
