package com.strugkail.mapper;


import com.strugkail.entity.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKeyWithBLOBs(Image record);

    int updateByPrimaryKey(Image record);

    List<Image> findList(Image image);

    /**
     * 获取总页数
     * @return
     */
    int getTotalSize();
}