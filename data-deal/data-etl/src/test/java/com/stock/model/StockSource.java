package com.stock.model;

public class StockSource extends Stock{
    private String jsonStr;
    private String reqUrl;

    public StockSource() {
    }

    public StockSource(String jsonStr, String reqUrl) {
        this.jsonStr = jsonStr;
        this.reqUrl = reqUrl;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }
}
