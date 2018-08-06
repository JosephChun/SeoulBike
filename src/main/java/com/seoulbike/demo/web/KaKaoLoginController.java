package com.seoulbike.demo.web;

import com.seoulbike.demo.domain.User;
import com.seoulbike.demo.domain.UserRepository;
import com.seoulbike.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.net.HttpURLConnection;
import java.util.Arrays;

@Controller
@RequestMapping("/api/kakao")
public class KaKaoLoginController {
    private static final Logger log = LoggerFactory.getLogger(KaKaoLoginController.class);

    @Resource(name = "userService")
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    private RestTemplate restTemplate = new RestTemplate();
    
    @GetMapping("/oauth")
    public String kakaoLogin(String code, HttpSession session) {
        log.info("code: {}", code);
        // 띄어쓰기 잘하자

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "ff16e8e6cd2f7b78da632c2d47250fd3");
        params.add("redirect_uri", "http://localhost:8080/api/kakao/oauth");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String response = restTemplate.postForObject("https://kauth.kakao.com/oauth/token", request, String.class);
        log.info("response: {}", response);
        return "redirect:/";
    }

}
