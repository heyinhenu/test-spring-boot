package demo;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.common.collect.Lists;

import demo.dev.DevModeTask;
import demo.ext.App;
import demo.ext.AppTask;
import demo.ext.RunMode;
import demo.ext.SpringApp;

@SpringBootApplication
// @Configurable
// @EnableAutoConfiguration
// @ComponentScan
public class DemoApplication {

    private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);
    private static App app = null;

    public static void main(final String[] args) {
        final App app = new SpringApp(new SpringApplication(DemoApplication.class));

        displayInfo(app.runMode(), args);

        if (app.runMode().isDevMode()) {
            LOG.info("init for dev mode");
            List<AppTask> ts = Lists.newArrayList(new DevModeTask());
            ts.stream().forEach(t -> t.init(app));
        }
        
        DemoApplication.setApp(app);

        app.run(args);
    }

    public static final App getApp() {
        if (app == null) {
            throw new RuntimeException("Don't entry!");
        }
        return app;
    }

    public static final App setApp(final App app) {
        DemoApplication.app = app;
        return app;
    }

    private static void displayInfo(RunMode runMode, String[] args) {
        LOG.info("#RunMode: {}", runMode);
        LOG.info("#Main args: {}", Arrays.toString(args));
    }
}
