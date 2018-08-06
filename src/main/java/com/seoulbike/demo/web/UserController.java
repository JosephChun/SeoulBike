package com.seoulbike.demo.web;

import com.seoulbike.demo.UnAuthenticationException;
import com.seoulbike.demo.UnAuthorizedException;
import com.seoulbike.demo.domain.User;
import com.seoulbike.demo.domain.UserRepository;
import com.seoulbike.demo.dto.UserDto;
import com.seoulbike.demo.security.LoginUser;
import com.seoulbike.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/login";
    }

    @PostMapping("/create")
    public String create(UserDto userDto, Model model) {
        try {
            userService.add(userDto);
        } catch (UnAuthorizedException e) {
            return "/user/form";
        }

        return "/user/login";
    }

    @GetMapping("/form")
    public String create() {
        return "/user/form";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession httpSession) {
        try {
            User loginUser = userService.login(userId, password);
            httpSession.setAttribute(HttpSessionUtils.USER_SESSION_KEY, loginUser);
            return "redirect: ";
        } catch (UnAuthenticationException e) {
            return "/user/login_failed";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return "redirect: ";
    }

    @GetMapping("")
    public String home() {
        return "index";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@LoginUser User loginUser, @PathVariable long id, Model model) {
        model.addAttribute("user", userService.findById(loginUser, id));
        return "/user/updatedForm";
    }

    @PutMapping("/{id}")
    public String update(@LoginUser User loginUser, @PathVariable long id, UserDto target) {
        userService.update(loginUser, id, target);
        return "redirect:/users";
    }
}
