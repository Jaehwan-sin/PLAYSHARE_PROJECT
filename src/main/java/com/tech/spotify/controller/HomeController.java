package com.tech.spotify.controller;

import com.tech.spotify.Repository.UserRepository;
import com.tech.spotify.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String check() {
        return "ok";
    }

    @GetMapping("/api/v1/user")
    public String user() {
        return "user";
    }

    @GetMapping("/main")
    public String main(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println("user (homecontroller) = " + user);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof OAuth2User) {
                OAuth2User oAuth2User = (OAuth2User) principal;
                model.addAttribute("isLoggedIn", true);
                model.addAttribute("userId", oAuth2User.getAttribute("name"));
                System.out.println("oAuth2User ID = " + oAuth2User);
                return "main";
            }
        }

        if (user != null) {
            Long userId = user.getId();
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("userId", userId);
            System.out.println("user id = " + userId);
            System.out.println("session.getId() = " + session.getId());
            System.out.println("model : " + model.getAttribute("isLoggedIn"));
        } else {
            model.addAttribute("isLoggedIn", false);
            System.out.println("User not logged in");
            System.out.println("model : " + model.getAttribute("isLoggedIn"));
        }

        log.info("Home controller check");
        return "main";
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user != null) {
            // 세션에서 사용자 정보를 제거하여 로그아웃 처리
            session.removeAttribute("user");

            // 로그아웃된 사용자 정보를 출력
            log.info("Logged out user: {}", user.getUsername());
        }

        return "main";
    }

}

