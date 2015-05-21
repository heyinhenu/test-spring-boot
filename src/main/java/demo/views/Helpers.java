package demo.views;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Options;
import com.google.common.collect.Maps;

import demo.DemoApplication;
import demo.ext.HandlebarsHelperSource;
import demo.ext.JavaLogable;

@Component
@HandlebarsHelperSource
public class Helpers implements JavaLogable {

    @Autowired
    private Environment env;

    public CharSequence env(Options options) throws IOException {
        Map<String, Object> context = Maps.newHashMap();
        context.put("runMode", DemoApplication.getApp().runMode());
        context.put("activeProfiles", env.getActiveProfiles());
        context.put("hotReload", hotReload());

        return options.fn(context);
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
