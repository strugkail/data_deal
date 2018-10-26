package com.strugkail.web.demo;

import com.strugkail.entity.Apply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * created by strugkail on 2018/7/31 0031
 *
 * @author strugkail
 */
@Controller
@RequestMapping("apply")
@Slf4j
public class ApplyController {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate secondaryJdbcTemplate;
    /**
     * 应聘时间与简历查阅列表
     * @param map
     * @return
     */
    @RequestMapping("list/date/{day}")
    public String list(@PathVariable("day") String day, Map<String,Object> map) {
        getFeedBack(map,day);
        getTypeNum(map,day);
        getAddressNum(map,day);
        getCompanySize(map,day);
        return "apply/model";
    }

    private void getCompanySize(Map<String, Object> map, String day) {
        String sql = "SELECT arrived,consulted,checked,Interested,day,type,check_time FROM t_apply WHERE day ="+day+" AND type=4";
        List<Apply> applyList = primaryJdbcTemplate.query(sql,new Apply());
        List<Integer> arrivedList = getIntegers(applyList);
        map.put("x_data_size","[\"创业型\",\"中大型\",\"已上市\",\"其他\"]");
        map.put("y_data_size",arrivedList.toString());
    }

    private void getAddressNum(Map<String, Object> map, String day) {
        String sql = "SELECT arrived,consulted,checked,Interested,day,type,check_time FROM t_apply WHERE day ="+day+" AND type=3";
        List<Apply> applyList = primaryJdbcTemplate.query(sql,new Apply());
        List<Integer> arrivedList = getIntegers(applyList);
        map.put("x_data_address","[\"浦东新区\",\"徐汇区\",\"杨浦区\",\"其他\"]");
        map.put("y_data_address",arrivedList.toString());
    }

    private void getTypeNum(Map<String, Object> map, String day) {
        String sql = "SELECT arrived,consulted,checked,Interested,day,type,check_time FROM t_apply WHERE day ="+day+" AND type=2";
        List<Apply> applyList = primaryJdbcTemplate.query(sql,new Apply());
        List<Integer> arrivedList = getIntegers(applyList);
        map.put("x_data_1","[\"金融\",\"互联网\",\"电子商务\",\"其他\"]");
        map.put("y_data",arrivedList.toString());
    }

    private List<Integer> getIntegers(List<Apply> applyList) {
        List<Integer> arrivedList = new ArrayList<>();
        addData(applyList, arrivedList, arrivedList, arrivedList, arrivedList);
        return arrivedList;
    }

    private void getFeedBack(Map<String, Object> map,String day) {
        String sql = "SELECT arrived,consulted,checked,Interested,day,type,check_time FROM t_apply WHERE day ="+day+" AND type=1 order BY check_time asc";
        List<Apply> applyList = primaryJdbcTemplate.query(sql,new Apply());
        List<Integer> arrivedList = new ArrayList<>();
        List<Integer> consultedList = new ArrayList<>();
        List<Integer> checkedList = new ArrayList<>();
        List<Integer> interestedList = new ArrayList<>();
        addData(applyList, arrivedList, consultedList, checkedList, interestedList);
        map.put("x_data_2","[\"早上\",\"中午\",\"晚上\"]");
        map.put("y_data_1",arrivedList.toString());
        map.put("y_data_2",consultedList.toString());
        map.put("y_data_3",checkedList.toString());
        map.put("y_data_4",interestedList.toString());
    }

    private void addData(List<Apply> applyList, List<Integer> arrivedList, List<Integer> consultedList, List<Integer> checkedList, List<Integer> interestedList) {
        applyList.forEach(apply -> {
            arrivedList.add(apply.getArrived());
            consultedList.add(apply.getConsulted());
            checkedList.add(apply.getChecked());
            interestedList.add(apply.getInterested());
        });
    }
}
