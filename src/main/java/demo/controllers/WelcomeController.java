package demo.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.DemoApplication;
import demo.ext.BaseController;

@Controller
public class WelcomeController extends BaseController {

    @Autowired
    private Environment env;

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, ModelMap model) {
        logger().debug("WelcomeController");

        request.getSession().setAttribute("now", new Date().toString());

        model.put("version", "1.1.21135");
        model.put("now", new Date());
        model.put("lastAccessedTime", request.getSession().getLastAccessedTime());
        model.put("sessionId", request.getSession().getId());
        model.put("creationTime", request.getSession().getCreationTime());
        model.put("session", request.getSession());

        model.put("runMode", DemoApplication.getApp().runMode());
        model.put("activeProfiles", env.getActiveProfiles());
        model.put("hotReload", hotReload());

        return "welcome";
    }

    private boolean hotReload() {
        try {
            Class.forName("org.springsource.loaded.agent.SpringLoadedAgent");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
