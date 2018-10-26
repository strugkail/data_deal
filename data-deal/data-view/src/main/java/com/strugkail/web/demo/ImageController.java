package com.strugkail.web.demo;

import com.strugkail.common.util.Page;
import com.strugkail.entity.Image;
import com.strugkail.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * created by strugkail on 2018/6/28 0028
 *
 * @author strugkail
 */
@Controller
@RequestMapping("image")
@Slf4j
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping("list")
    public String getAllImage(Map<String,Object> map){
        return "demo/image";
    }
    @RequestMapping("table")
    public String getTable(Map<String,Object> map,HttpServletRequest request,
                           String startDate,String endDate,
                           HttpServletResponse response,Image image){
        Page<Image> page = new Page<Image>(request, response);
        try {
            page.setPageNo(image.getPageNo());
            page.setPageSize(image.getPageSize());
            Page<Image> imagePage = imageService.getImagePage(page,image);
            map.put("imageList",imagePage.getList());
            map.put("page",imagePage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "demo/table";
    }
    @RequestMapping("delete")
    public String deleteById(Map<String,Object> map){
        try {
            imageService.updateById(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "demo/table";
    }

}
