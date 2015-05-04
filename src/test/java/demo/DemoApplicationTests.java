package demo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import demo.dev.MockRedisServer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {

    private static final MockRedisServer mock = new MockRedisServer(6379);

    @BeforeClass
    public static void before() {
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
