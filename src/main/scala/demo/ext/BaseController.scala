package demo.ext

import java.util.{ Map => JMap }
import scala.collection.JavaConverters._

import org.springframework.web.servlet.ModelAndView

abstract class BaseController extends Logable {

  protected def view(path: String): ModelAndView = {
    new ModelAndView(path)
  }

  protected def view(path: String, context: (String, AnyRef)*): ModelAndView = {
    view(path, Map(context: _*))
  }

  protected def view(path: String, context: Map[String, AnyRef]): ModelAndView = {
    new ModelAndView(path, context.asJava)
  }

  protected def view(path: String, k1: String, v1: AnyRef): ModelAndView = {
    view(path, Map(k1 -> v1))
  }

  protected def view(path: String, k1: String, v1: AnyRef, k2: String, v2: AnyRef): ModelAndView = {
    view(path, Map(k1 -> v1, k2 -> v2))
  }

  protected def view(path: String, k1: String, v1: AnyRef, k2: String, v2: AnyRef, k3: String, v3: AnyRef): ModelAndView = {
    view(path, Map(k1 -> v1, k2 -> v2, k3 -> v3))
  }

  protected def view(path: String, k1: String, v1: AnyRef, k2: String, v2: AnyRef, k3: String, v3: AnyRef, k4: String, v4: AnyRef): ModelAndView = {
    view(path, Map(k1 -> v1, k2 -> v2, k3 -> v3, k4 -> v4))
  }

  protected def view(path: String, context: JMap[String, AnyRef]): ModelAndView = {
    new ModelAndView(path, context)
  }

  protected def redirect(path: String): String = {
    "redirect:" + path
  }
}
