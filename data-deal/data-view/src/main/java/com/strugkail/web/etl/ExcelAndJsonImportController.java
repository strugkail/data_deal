package com.strugkail.web.etl;

import com.strugkail.common.util.ExcelUtil;
import com.strugkail.common.util.JsonResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 导入json或者excel数据，动态分析图表
 */
@Controller
@RequestMapping("data")
public class ExcelAndJsonImportController {
    @RequestMapping("show")
    public String importData(MultipartFile rechargeFile,
                                  HttpServletRequest request) {
        return "apply/import";
    }
    @RequestMapping("excel")
    public String importExcelData(MultipartFile rechargeFile,
                                  HttpServletRequest request) {
        return "apply/excel-import";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponseModel upload(@RequestParam(value = "excelFile", required = false) MultipartFile file,
                                    HttpServletRequest request) {
        JsonResponseModel jrm = new JsonResponseModel();
        try {
            MultipartRequest multipartRequest = (MultipartRequest) request;
            MultipartFile excelFile = multipartRequest.getFile("excelFile");
            if (excelFile != null) {
                List<List<String>> results = new ArrayList<>();
                List<List<String>> datas = ExcelUtil.readXlsx(excelFile.getInputStream());
                //TODO: 读到的数据都在datas里面，根据实际业务逻辑做相应处理<br>
                if (datas != null && datas.size() > 0) {
                for(int i=0;i<datas.size();i++){
                    List<String> strs = datas.get(i);
                    if(i>2){
                        List<String> stringList = new ArrayList<>();
                        for(int j = 0;j<strs.size();j++){
                            if(j==3||j>6)
                            stringList.add(strs.get(j));
                        }
                        results.add(stringList);
                    }
                }
                    jrm.success(results);
                }
            } else {
                jrm.fail("fail");
            }
        } catch (Exception e) {
            jrm.fail("exception");
        }
        return jrm;
    }
}
