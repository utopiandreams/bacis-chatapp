package study.basic.chatapp.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.basic.chatapp.entity.User;
import study.basic.chatapp.repository.UserRepository;
import study.basic.chatapp.service.UserDetailsImpl;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @PostMapping("/join")
    public String join(HttpServletRequest req) {
        String username = req.getParameter("username");
        System.out.println("username = " + username);
        if(userRepository.findByUsername(username).isPresent()){
            return "이미 존재하는 대화명 입니다.";
        }
        String password = req.getParameter("password");
        String encodedPassword = encoder.encode(password);
        System.out.println("encoder = " + encoder);
        User newUser = User.builder().username(username).password(encodedPassword).build();
        userRepository.save(newUser);
        return "대화명이 등록 되었습니다.";
    }

    @RequestMapping("/fail")
    public String fail(HttpServletRequest req) {
        return "fail";
    }

    @GetMapping("/me")
    public ResponseEntity<Long> me(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = principal.getUserId();
        return ResponseEntity.ok().body(userId);
    }
}
