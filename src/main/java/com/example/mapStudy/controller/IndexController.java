package com.example.mapStudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.controller
 * @Author: Noah
 * @CreateTime: 2022-11-26  17:57
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String login(Model model) {
//        return "request mapping passwordList.html";
        return "index.html";
    }
}
