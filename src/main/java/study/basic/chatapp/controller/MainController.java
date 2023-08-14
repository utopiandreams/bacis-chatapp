package study.basic.chatapp.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MainController {

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/chat")
    public String chatHome() {
        return "chat";
    }

    @GetMapping("/")
    public void index(HttpServletResponse res) throws IOException {
        res.sendRedirect("/chat");
    }

}
