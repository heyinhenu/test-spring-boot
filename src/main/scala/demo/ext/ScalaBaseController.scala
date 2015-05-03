package demo.ext

import scala.collection.JavaConverters._

import org.springframework.web.servlet.ModelAndView

abstract class ScalaBaseController extends BaseController {

  protected def view(path: String, context: (String, AnyRef)*): ModelAndView = {
    new ModelAndView(path, Map(context: _*).asJava)
  }

  protected def view(path: String, context: Map[String, AnyRef]): ModelAndView = {
    new ModelAndView(path, context.asJava)
  }

  protected def view(path: String): ModelAndView = {
    new ModelAndView(path)
  }
}