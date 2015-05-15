package demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.ext.BaseController;

@Controller
@RequestMapping("/test/handlebars")
public class HandlebarsTestController extends BaseController {

    @RequestMapping("")
    public String index() {
        return "/test/handlebars/index";
    }
}
