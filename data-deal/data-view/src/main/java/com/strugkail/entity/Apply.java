package com.strugkail.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * created by strugkail on 2018/7/31 0031
 *
 * @author strugkail
 */
public class Apply implements RowMapper {
    private int id;
    private int arrived;
    private int consulted;
    private int checked;
    private int Interested;
    private int day;
    private String type;
    @DateTimeFormat()
    private Date checkTime;

    public Apply() {
    }

    public Apply(int arrived, int consulted, int checked, int interested, int day, String type, Date checkTime) {
        this.arrived = arrived;
        this.consulted = consulted;
        this.checked = checked;
        Interested = interested;
        this.day = day;
        this.type = type;
        this.checkTime = checkTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrived() {
        return arrived;
    }

    public void setArrived(int arrived) {
        this.arrived = arrived;
    }

    public int getConsulted() {
        return consulted;
    }

    public void setConsulted(int consulted) {
        this.consulted = consulted;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getInterested() {
        return Interested;
    }

    public void setInterested(int interested) {
        Interested = interested;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    @Override
    public Apply mapRow(ResultSet rs, int i) throws SQLException {
        return new Apply(rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getInt(5),
                rs.getString(6),
                rs.getDate(7));
    }
}
