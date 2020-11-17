package com.canthny.mysql.data.init.domain;

import java.util.Date;
import java.util.Random;

public class PtOrderInfo {
    private Long id;

    private Long userId;

    private String orderNo;

    private String buyerAccountNo;

    private String sellerAccountNo;

    private Long amount;

    private Integer orderType;

    private Integer orderStatus;

    private String orderTitle;

    private String remark;

    private Date createdDate;

    private Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getBuyerAccountNo() {
        return buyerAccountNo;
    }

    public void setBuyerAccountNo(String buyerAccountNo) {
        this.buyerAccountNo = buyerAccountNo == null ? null : buyerAccountNo.trim();
    }

    public String getSellerAccountNo() {
        return sellerAccountNo;
    }

    public void setSellerAccountNo(String sellerAccountNo) {
        this.sellerAccountNo = sellerAccountNo == null ? null : sellerAccountNo.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle == null ? null : orderTitle.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public static void main(String[] args) {
        String goodsCode = String.valueOf((int)(Math.random()*10000+10));
        int amount=(int)(Math.random()*10+1);
        int a1 = amount-amount/2;
        int a2 = amount/2;
        System.out.println(goodsCode);
        System.out.println(amount);
        System.out.println(a1);
        System.out.println(a2);
    }
}