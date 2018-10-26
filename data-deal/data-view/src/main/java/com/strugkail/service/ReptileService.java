package com.strugkail.service;
import com.strugkail.mapper.ImageMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * created by strugkail on 2018/6/28 0028
 *
 * @author strugkail
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
@MapperScan("com.reptile.mapper")
public class ReptileService {
    @Autowired
    private ImageMapper imageMapper;


//    public void startReptile(String url) {
//        List<String> list = MyCrawler.crawling(new String[]{url});
//        Date date = new Date();
//        list.forEach(resultUrl -> {
//            String baseStr = Base64Img.image2Base64(resultUrl);
//            imageMapper.insert(new Image(resultUrl, date, "strugkail", "0",baseStr));
//        });
//    }
}
