package com.strugkail.web.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * created by strugkail on 2018/6/21 0021
 * @author strugkail
 */
@Controller
@RequestMapping("freemarker")
@Slf4j
public class FreemarkerController {

    @RequestMapping("hello")
    public String hello(Map<String,Object> map) {
        map.put("msg", "Hello Freemarker");
        return "demo/hello";
    }
    @RequestMapping("demo")
    public String demo(Map<String,Object> map) {
        map.put("msg", "第一个案例");
        return "demo/demo";
    }

}
