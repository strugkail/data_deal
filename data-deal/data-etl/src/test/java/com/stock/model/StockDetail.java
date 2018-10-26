package com.stock.model;

public class StockDetail extends Stock{
    private Double open;
    private Double close;
    private Double highest;
    private Double lowest;
    private Double volume;
    private Double rewardPrice;

    public StockDetail() {
    }

    public StockDetail(Double open, Double close, Double highest, Double lowest, Double volume, Double rewardPrice) {
        this.open = open;
        this.close = close;
        this.highest = highest;
        this.lowest = lowest;
        this.volume = volume;
        this.rewardPrice = rewardPrice;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getHighest() {
        return highest;
    }

    public void setHighest(Double highest) {
        this.highest = highest;
    }

    public Double getLowest() {
        return lowest;
    }

    public void setLowest(Double lowest) {
        this.lowest = lowest;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getRewardPrice() {
        return rewardPrice;
    }

    public void setRewardPrice(Double rewardPrice) {
        this.rewardPrice = rewardPrice;
    }
}
