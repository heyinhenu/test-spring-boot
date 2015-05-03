package demo.controllers;

import java.util.stream.IntStream;

import javaslang.Function1;
import javaslang.Function2;
import javaslang.collection.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import demo.ext.BaseController;

@Controller
@RequestMapping("/javaslang")
public class JavaslangTestController extends BaseController {

    @RequestMapping("")
    public ModelAndView index() {
        final Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        // add2.apply(1) = 3
        final Function1<Integer, Integer> add2 = sum.curried().apply(2);

        return view("/javaslang/index", 
                "sum", add2.apply(100),
                "sum2", List.of(1, 2, 3, 4).sum(),
                "sum3", IntStream.of(1, 2, 3).sum());
    }
}
