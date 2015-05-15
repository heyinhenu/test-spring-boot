package demo.config

import scala.collection.JavaConverters._
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.context.annotation.Configuration
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver
import demo.ext.Logable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import javax.annotation.PostConstruct
import demo.ext.HandlebarsHelperSource

@Configuration
@ConditionalOnClass(Array(classOf[HandlebarsViewResolver]))
@ConditionalOnWebApplication
class HandlebarsHelperConfig extends Logable {

  @Autowired
  private var handlebarsViewResolver: HandlebarsViewResolver = _

  @Autowired
  private var ctx: ApplicationContext = _

  @PostConstruct
  def registerHelper() {
    val helperSources = ctx.getBeansWithAnnotation(classOf[HandlebarsHelperSource])

    logger.info("registerHelper:{}", helperSources.size())
    
    for (hs <- helperSources.values().asScala) {
      handlebarsViewResolver.registerHelpers(hs)
    }
  }
}
