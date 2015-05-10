package demo.controllers

import java.text.SimpleDateFormat
import java.util.Date
import scala.beans.BeanProperty
import scala.collection.JavaConverters._
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.ui.Model
import org.springframework.web.servlet.ModelAndView
import demo.ext.BaseController
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping(Array("/test/scala"))
class TestScalaController extends BaseController {

  @RequestMapping(Array(""))
  @ResponseBody
  def index(): String = {
    s"V21: Hello, from scala (at ${new Date()})"
  }

  @RequestMapping(Array("/handlebars"))
  def handlebars(model: ModelMap): String = {
    model.put("now", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date))
    model.put("list", List("a1223", "b", "c", "d", "e", "f").asJava)

    "/test/scala/handlebars"
  }

  @RequestMapping(Array("/json"))
  def json(@RequestParam(value = "t", defaultValue = "java") t: String, model: ModelMap) /*: ModelAndView*/ = {
    logger.debug("t:{}", t)
    val users = List(User("itang", 39), User("tqibm", 18))

    view("/test/scala/json",
      "t" -> t,
      "users" -> (t match {
        case "java" => users.asJava
        case _ => users
      }))
  }

  @RequestMapping(Array("/json/data"))
  @ResponseBody
  def jsonData(@RequestParam(value = "t", defaultValue = "java") t: String) = {
    t match {
      case "java" => List(User("itang"), User("tqibm", 34)).asJava
      case _ => {
        logger.debug("type: scala")
        List(UserNative("itang"), UserNative("tqibm", 34))
      }
    }
  }
}

case class User(
  @BeanProperty name: String,
  @BeanProperty age: Int = 100)

case class UserNative(
  name: String,
  age: Int = 100)


