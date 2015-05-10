package demo.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.module.scala.DefaultScalaModule

@Configuration
@ConditionalOnClass(Array(classOf[DefaultScalaModule]))
class ScalaConfig {

  @Bean
  def defaultScalaModule(): Module = {
    DefaultScalaModule
  }
}
