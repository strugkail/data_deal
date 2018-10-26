package com.strugkail.web.etl;

import com.strugkail.service.ReptileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * created by strugkail on 2018/6/28 0028
 *
 * @author strugkail
 */
@Controller
@RequestMapping("reptile")
@Slf4j
public class ReptileController {
    @Autowired
    private ReptileService reptileService;
    @RequestMapping("start")
    @ResponseBody
    public String startReptile(@RequestParam("url") String url){
        return "success";
    }

}
