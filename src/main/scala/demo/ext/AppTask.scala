package demo.ext

import org.springframework.boot.SpringApplication
import org.springframework.core.env.Environment

abstract class AppTask {
  def init(app: App): Unit = {}

  def prepare(app: App, env: Environment): Unit = {}

  def start(): Unit = {}

  def after(): Unit = {}

  def stop() = {}
}
