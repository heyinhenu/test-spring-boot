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

    public static void main(final String[] args) {
        final App app = SpringApp.INSTANCE();
        displayInfo(app.runMode(), args);

        final SpringApplication application = new SpringApplication(DemoApplication.class);

        if (app.runMode().isDevMode()) {
            LOG.info("init for dev mode");
            application.setAdditionalProfiles("dev");
            List<AppTask> ts = Lists.newArrayList(new DevModeTask());
            ts.stream().forEach(t -> t.init(application));
        } else {
            application.setAdditionalProfiles("prod");
        }
        application.run(args);
    }

    private static void displayInfo(RunMode runMode, String[] args) {
        LOG.info("#RunMode: {}", runMode);
        LOG.info("#Main args: {}", Arrays.toString(args));
    }
}
