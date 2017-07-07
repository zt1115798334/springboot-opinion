package com.zt.opinion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangtong on 2017/7/7.
 */
@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return  "index";
    }
}
