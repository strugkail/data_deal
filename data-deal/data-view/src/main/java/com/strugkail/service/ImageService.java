package com.strugkail.service;

import com.strugkail.common.util.Page;
import com.strugkail.entity.Image;
import com.strugkail.mapper.ImageMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by strugkail on 2018/6/28 0028
 *
 * @author strugkail
 */
@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class ImageService {
    @Autowired
    private ImageMapper imageMapper;
    /**
     *
     * @Title: getList
     * @Description: 从数据库中获取所有商品类型列表
     * @return
     * @throws Exception
     */
    public Page<Image> getImagePage(Page<Image> imagePage, Image image) throws Exception {
        int pageNo = imagePage.getPageNo();
        int pageSize = imagePage.getPageSize();
        image.setPageNo((pageNo-1)*pageSize);
        //使用分页插件,核心代码就这一行 （先不用）
//        PageHelper.startPage(pageNum, pageSize);
        // 获取list
        List<Image> imageList = imageMapper.findList(image);
        // 获取总页数
        int totalSize = imageMapper.getTotalSize();
//        // 开始页码（从第几页开始）
//        int startIndex = (pageNo - 1) * pageSize;
//        // 结束页码
//        int endIndex = (pageNo * pageSize) > totalSize ? totalSize : (pageNo * pageSize);
//        if (startIndex >= endIndex) {
//            imagePage.setPageNo(1);
//        }
        imagePage.setCount(totalSize);
        imagePage.setList(imageList);
        return imagePage;
    }

    /**
     *
     * @param id
     */
    public void updateById(Integer id) {
        imageMapper.updateByPrimaryKey(new Image(id));
    }
}
