package com.nus.controller.payment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlipayController {

    @GetMapping("/payment")
    public static String index() {
        return "";
    }
}
