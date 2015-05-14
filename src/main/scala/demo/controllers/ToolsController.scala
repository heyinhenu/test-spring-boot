package demo.controllers

import org.springframework.stereotype.Controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import demo.ext.BaseController

@Controller
class ToolsController extends BaseController {

  @RequestMapping(value = Array("/ping"), produces = Array("text/plain"))
  @ResponseBody
  def ping() = {
    "pong"
  }
}