package demo.ext

import org.springframework.boot.SpringApplication

trait App {
  def runMode: RunMode

  def application: SpringApplication

  def run(args: Array[String]): Unit
}

case class SpringApp(application: SpringApplication) extends App with Logable {
  override val runMode: RunMode = {
    val r = RunMode.fromString(System.getProperty(RunMode.RUN_MODE_KEY))
    if (!r.isPresent) {
      logger.warn(s"未设置${RunMode.RUN_MODE_KEY}属性, 默认为RunMode为: ${RunMode.Dev}")
      RunMode.Dev
    } else {
      r.get
    }
  }

  // init
  application.setAdditionalProfiles(runMode.toString().toLowerCase())

  override def run(args: Array[String]): Unit = {
    application.run(args: _*)
  }
}
