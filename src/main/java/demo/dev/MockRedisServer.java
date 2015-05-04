package demo.dev;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.embedded.RedisServer;

public class MockRedisServer {
    private static Logger LOG = LoggerFactory.getLogger(MockRedisServer.class);

    private final int port;
    private RedisServer redisServer = null;

    public MockRedisServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            redisServer = new RedisServer(port);
            if (!redisServer.isActive()) {
                redisServer.start();
                LOG.info("Mock Redis start");
            }
        } catch (IOException e) {
            throw new RuntimeException("error", e);
        }
    }

    public void stop() {
        if (redisServer != null) {
            LOG.info("Redis stop");
            redisServer.stop();
            redisServer = null;
        }
    }

    public int getPort() {
        return port;
    }
}
