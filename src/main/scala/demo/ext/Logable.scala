package demo.ext

import org.slf4j.LoggerFactory

trait Logable {

  val logger = LoggerFactory.getLogger(this.getClass)
}
