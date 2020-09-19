package com.company;

public class reqdata {

    String goodsId= "";
    String payWay="01";
    String amount="10.00";
    String saleTypes= "C";
    String points= "0";
    String beginTime= "1596765600000";
    String imei= "";
    String sourceChannel= "955000300";
    String proFlag= "";
    String scene= "";
    String promoterCode= "";
    String maxcash= "";
    String reChangeNo="15602968825";

    public String getReChangeNo() {
        return reChangeNo;
    }

    public void setReChangeNo(String reChangeNo) {
        this.reChangeNo = reChangeNo;
    }

    public reqdata(String goodsId) {
        this.goodsId = goodsId;
    }
    public reqdata() {

    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSaleTypes() {
        return saleTypes;
    }

    public void setSaleTypes(String saleTypes) {
        this.saleTypes = saleTypes;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getProFlag() {
        return proFlag;
    }

    public void setProFlag(String proFlag) {
        this.proFlag = proFlag;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getPromoterCode() {
        return promoterCode;
    }

    public void setPromoterCode(String promoterCode) {
        this.promoterCode = promoterCode;
    }

    public String getMaxcash() {
        return maxcash;
    }

    public void setMaxcash(String maxcash) {
        this.maxcash = maxcash;
    }
}
