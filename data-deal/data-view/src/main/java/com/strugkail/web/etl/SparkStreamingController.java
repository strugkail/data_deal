package com.strugkail.web.etl;

import com.strugkail.common.util.JsonResponseModel;
import com.strugkail.service.KafkaStreamingJApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by strugkail on 2018/6/19 0019
 * @author strugkail
 */
@RestController
@RequestMapping("java")
@Slf4j
public class SparkStreamingController {

    @Autowired
    private KafkaStreamingJApp kafkaStreamingJApp;
    /**
     * 启动 spark streaming
     * 使用java api
     * @return
     */
    @RequestMapping("start")
    @ResponseBody
    public JsonResponseModel startWithJava() {
        try {
            kafkaStreamingJApp.initContent(null).wordCount(",");
            kafkaStreamingJApp.dealStart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new JsonResponseModel().success("spark streaming 启动成功");
    }
}
