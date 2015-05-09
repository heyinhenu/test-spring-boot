package demo.ext

trait App {
  def runMode: RunMode
}

object SpringApp extends App with Logable {

  val runMode: RunMode = {
    val r = RunMode.fromString(System.getProperty(RunMode.RUN_MODE_KEY))
    if (!r.isPresent) {
      logger.warn(s"未设置${RunMode.RUN_MODE_KEY}属性, 默认为RunMode为: ${RunMode.Dev}")
      RunMode.Dev
    } else {
      r.get
    }
  }

  val instance = this
}
