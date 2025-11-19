package chora.auth.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SasCallbackController {
    @GetMapping("/callback")
    @ResponseBody
    public String handleCallback(@RequestParam(required = false) String code,
                                 @RequestParam(required = false) String error) {
        if (error != null) {
            return "Error: " + error;
        }
        if (code != null) {
            return "Authorization code: " + code;
        }
        return "No code received";
    }
}
