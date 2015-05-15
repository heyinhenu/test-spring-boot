package demo.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.ext.BaseController;

@Controller
@RequestMapping("/test/handlebars")
public class HandlebarsTestController extends BaseController {

    @RequestMapping("")
    public String index(ModelMap model) {
        model.put("now", new Date());
        return "/test/handlebars/index";
    }
}
