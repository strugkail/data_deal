package com.strugkail.web.etl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.strugkail.common.util.HttpUtil;
import com.strugkail.common.util.JsonResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.util.parsing.json.JSON;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("draw")
public class DrawKlineController {
    private static final String REQUEST_BASE_URL = "http://img1.money.126.net/data/hs/kline/day/history/";
    private static final String DOWNLOAD_BASE_URL = "http://quotes.money.163.com/service/chddata.html?code=0601857&start=20071105&end=20180828&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP";
    private static final String CODE_BASE_URL = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?ma=no";
    private static Map<String,String> codeMap = null;
    @RequestMapping("dayKline")
    public String importData() {
        return "apply/drawKline";
    }

    @RequestMapping("minKline")
    public String redirectMinKline(Map<String,Object> map) {
        if(codeMap==null){
            initData();
        }
        map.put("codeMap",codeMap);
        return "apply/drawMinKline";
    }
    /**
     * 格式： ["20180704",11045.46,10868.08,11066.29,10835.13,7660690233,-1.99]
     *
     * @param year
     * @param code
     * @return
     */
    @RequestMapping("getData")
    @ResponseBody
    public JsonResponseModel getRequestData(@RequestParam("year") String year, @RequestParam("code") String code) {
        JsonResponseModel jrm = new JsonResponseModel();
        List<List<Object>> list = new ArrayList<>();
        try {
            String returnData = HttpUtil.get(REQUEST_BASE_URL + year + "/" + code + ".json");
            JSONObject json = JSONObject.parseObject(returnData);
            JSONArray array = (JSONArray) json.get("data");
            for (int i = 0; i < array.size(); i++) {
                List<Object> objects = new ArrayList<>();
                JSONArray jsonArray = (JSONArray) array.get(i);
                for (int j = 0; j < jsonArray.size(); j++) {
                    if (j < 5)
                        objects.add(jsonArray.get(j));
                }
                list.add(objects);
            }
            jrm.success(list);
        } catch (Exception e) {
            jrm.fail(e.getMessage());
            e.printStackTrace();
        }
        return jrm;
    }

    /**
     * 格式： {day:"2018-06-22 10:30:00",open:"31.700",high:"34.300",low:"31.200",close:"33.740",volume:"1175626"}
     *
     * @param scale   分钟数
     * @param code    股票代码
     * @param datalen 获取数据节点   最多1023个节点
     * @return
     */
    @RequestMapping("getCodeData")
    @ResponseBody
    public JsonResponseModel getCodeData(@RequestParam("scale") String scale,
                                         @RequestParam("code") String code,
                                         @RequestParam("datalen") String datalen) {
        JsonResponseModel jrm = new JsonResponseModel();
        List<List<Object>> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder(CODE_BASE_URL);
        if (StringUtils.isNotBlank(scale) &&
                StringUtils.isNotBlank(code) &&
                StringUtils.isNotBlank(datalen)) {
            builder.append("&symbol=" + code);
            builder.append("&scale=" + scale);
            builder.append("&datalen=" + datalen);
        }
        try {
            String returnData = HttpUtil.get(builder.toString());
            JSONArray array = (JSONArray) JSONArray.parse(returnData);
            for (int i = 0; i < array.size(); i++) {
                List<Object> objects = new ArrayList<>();
                JSONObject jsonObject = (JSONObject) array.get(i);
                objects.add(jsonObject.get("day"));
                objects.add(jsonObject.get("open"));
                objects.add(jsonObject.get("close"));
                objects.add(jsonObject.get("low"));
                objects.add(jsonObject.get("high"));
                list.add(objects);
            }
            jrm.success(list);
        } catch (Exception e) {
            jrm.fail(e.getMessage());
            e.printStackTrace();
        }
        return jrm;
    }
    private void initData(){
        codeMap = new HashMap<>();
        try {
            String filePath = this.getClass().getClassLoader().getResource("templates").getPath()+"/股票代码.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rsRows = sheet.getPhysicalNumberOfRows();// 行
            XSSFRow hssfRow = sheet.getRow(0);//得到一行，进而下一步得到列数
            int rsColumns = hssfRow.getPhysicalNumberOfCells();// 列
            String codeType = "";
            for (int j = 0; j < rsColumns; j++)
            {
                if(j==0)    codeType = "sz";    else    codeType = "sh";
                for (int i = 1; i < rsRows; i++)
                {
                    XSSFCell c1 = sheet.getRow(i).getCell(j);// 获取第i行数据的第j列
                    if(c1!=null){
                        String key = c1.getStringCellValue();
                        String value = codeType + key.substring(key.indexOf("(")+1,key.length()-1);
                        key = key.substring(0,key.indexOf("("));
                        codeMap.put(key,value);
                        System.out.println(value);
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
