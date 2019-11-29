package com.canthny.shardingsphere.springboot.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Description： TODO
 * Created By tanghao on 2019/11/29
 */
@Data
@Entity
@Table(name = "pt_order_goods")
@DynamicInsert
@DynamicUpdate
public class OrderGood extends BaseEntity{
    private static final long serialVersionUID = -6585387980307418251L;

    private String orderNo;

    private String goodsCode;

    private Integer goodsNum;

    private Long goodsPrice;

    private String remark;
}
