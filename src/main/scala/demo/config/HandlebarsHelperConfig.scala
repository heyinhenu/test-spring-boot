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
import com.github.jknack.handlebars.Helper
import demo.ext.HandlebarsHelper

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
    logger.info("registerHelpers:{}", helperSources.size())
    for (hs <- helperSources.values().asScala) {
      handlebarsViewResolver.registerHelpers(hs)
    }

    val helpers = ctx.getBeansWithAnnotation(classOf[HandlebarsHelper])
    logger.info("registerHelper:{}", helpers.size())
    for (hs <- helpers.values().asScala) {
      val helper = hs.asInstanceOf[Helper[_]]
      handlebarsViewResolver.registerHelper(getHelperName(helper), helper)
    }
  }

  private def getHelperName(h: Helper[_]): String = {
    val ann = h.getClass.getAnnotation(classOf[HandlebarsHelper])
    ann.value()
  }
}
