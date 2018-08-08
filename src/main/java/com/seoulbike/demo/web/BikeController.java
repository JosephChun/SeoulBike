package com.seoulbike.demo.web;

import com.seoulbike.demo.domain.User;
import com.seoulbike.demo.security.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bikes")
public class BikeController {

    @PostMapping("")
    public String bikeList(@LoginUser User loginUser) {
        return "/bike/list";
    }
}
