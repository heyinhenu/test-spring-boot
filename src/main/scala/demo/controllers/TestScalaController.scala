package demo.controllers

import java.util.Date
import scala.collection.JavaConverters._
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{ ResponseBody, RequestMapping }
import org.springframework.ui.ModelMap
import demo.ext.BaseController
import java.text.SimpleDateFormat

@Controller
@RequestMapping(Array("/test/scala"))
class TestScalaController extends BaseController {

  @RequestMapping(Array(""))
  @ResponseBody
  def index(): String = {
    s"V21: Hello, from scala (at ${new Date()})"
  }

  @RequestMapping(Array("/handlebars"))
  def index(model: ModelMap): String = {
    model.put("now", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date))
    model.put("list", List("a1223", "b", "c", "d", "e", "f").asJava)

    "/test/scala/handlebars"
  }
}
