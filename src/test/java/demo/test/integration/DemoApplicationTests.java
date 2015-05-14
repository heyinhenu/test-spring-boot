package demo.test.integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import demo.DemoApplication;
import demo.dev.MockRedisServer;
import demo.ext.RunMode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
@ActiveProfiles("unittest")
public class DemoApplicationTests {

    private static final MockRedisServer mock = new MockRedisServer(6380);

    @BeforeClass
    public static void before() {
        System.setProperty(RunMode.RUN_MODE_KEY, RunMode.RUN_MODE_TEST);
        mock.start();
    }

    @AfterClass
    public static void after() {
        mock.stop();
    }

    @Test
    public void contextLoads() {
    }
}
