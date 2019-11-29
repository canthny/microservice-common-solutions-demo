package com.canthny.shardingsphere.springboot.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Description： 订单信息
 * Created By tanghao on 2019/11/29
 */
@Data
@Entity
@Table(name = "pt_order_info")
@DynamicInsert
@DynamicUpdate
public class OrderInfo extends BaseEntity{
    private static final long serialVersionUID = -4017626139614461152L;

    private String orderNo;

    private String buyerAccountNo;

    private String sellerAccountNo;

    private Long amount;

    private Integer orderType;

    private Integer orderStatus;

    private String orderTitle;

    private String remark;
}
