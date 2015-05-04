package demo.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import demo.utils.Utils;

public class RedisBootstrapListener implements ApplicationListener<ApplicationStartedEvent> {

    private static Logger LOG = LoggerFactory.getLogger(RedisBootstrapListener.class);
    private static final int DEFAULT_REDIS_PORT = 6379;

    private MockRedisServer mockRedisServer = new MockRedisServer(DEFAULT_REDIS_PORT);

    public RedisBootstrapListener() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            destroy();
        }));
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        LOG.debug("ApplicationStartedEvent: {}", event);
        if (!Utils.availablePort(DEFAULT_REDIS_PORT)) {
            LOG.warn("Redis port unavailabled, check Redis service have started...");
            return;
        }

        LOG.info("start redis server...");
        try {
            mockRedisServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        mockRedisServer.stop();
        LOG.info("Redis stop");
    }
}
