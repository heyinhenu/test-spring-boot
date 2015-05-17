package demo.dev

import org.springframework.boot.SpringApplication

import demo.ext.AppTask
import demo.ext.Logable

class DevModeTask extends AppTask with Logable {

  private var redisServer: MockRedisServer = null

  override def init(application: SpringApplication): Unit = {
    val port = 6379
    logger.debug("start mock redis")
    redisServer = new MockRedisServer(port);
    redisServer.start();
    logger.debug("end start mock redis")
  }

  override def stop(): Unit =
    if (redisServer != null) {
      redisServer.stop()
    }
}
