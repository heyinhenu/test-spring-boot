package demo.ext

import org.springframework.boot.SpringApplication
import org.springframework.core.env.Environment

abstract class AppTask {
  def init(application: SpringApplication): Unit = {}
  def prepare(application: SpringApplication, env: Environment): Unit = {}
  def start(): Unit = {}
  def after(): Unit = {}
  def stop() = {}
}
