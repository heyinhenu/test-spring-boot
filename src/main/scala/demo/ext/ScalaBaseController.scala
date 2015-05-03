package demo.ext

import scala.collection.JavaConverters._

import org.springframework.web.servlet.ModelAndView

abstract class ScalaBaseController extends BaseController {

  protected def view(path: String): ModelAndView = {
    new ModelAndView(path)
  }

  protected def view(path: String, context: (String, AnyRef)*): ModelAndView = {
    view(path, Map(context: _*))
  }

  protected def view(path: String, context: Map[String, AnyRef]): ModelAndView = {
    new ModelAndView(path, context.asJava)
  }
}
