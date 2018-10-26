package com.strugkail.web.etl;


import com.strugkail.common.util.JsonResponseModel;
import com.strugkail.common.util.KafkaProducer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by strugkail on 2018/6/19 0019
 * @author strugkail
 */
@RestController
@RequestMapping("kafka")
public class KafkaProducerController {
    @RequestMapping("/send")
    @ResponseBody
    public JsonResponseModel sendMessage(@RequestParam("message") String message) {
        if(StringUtils.isNotBlank(message)){
            KafkaProducer.sendSingleMessage(message);
            System.out.println(message+"--------------------已发送");
        }
        return new JsonResponseModel().success("发送成功");
    }
    @RequestMapping("/hello")
    public String hello() {
        return "hello,this is a springboot demo";
    }
}
