package com.strugkail.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Image implements RowMapper {
    private Integer id;

    private String imgurl;

    private Date createTime;

    private String createName;

    private String delFlag;

    private String base64str;

    private int pageNo = 1;

    private int pageSize = 30;

    private String dateStart;

    private String dateEnd;


    public Image(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Image(String imgurl, Date createTime, String createName, String delFlag, String base64str) {
        this.imgurl = imgurl;
        this.createTime = createTime;
        this.createName = createName;
        this.delFlag = delFlag;
        this.base64str = base64str;
    }

    public Image() {
    }

    public Image(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getBase64str() {
        return base64str;
    }

    public void setBase64str(String base64str) {
        this.base64str = base64str == null ? null : base64str.trim();
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imgurl='" + imgurl + '\'' +
                ", createTime=" + createTime +
                ", createName='" + createName + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", base64str='" + base64str + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                '}';
    }
    @Override
    public Image mapRow(ResultSet rs, int i) throws SQLException {
        return new Image(rs.getString(1),
                rs.getDate(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5));
    }
}