package com.stock.model;

public class Stock {
    private Integer id;
    private Integer code;
    private String date;
    private Character type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }
}
