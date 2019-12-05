package com.canthny.shardingsphere.springboot.jpa.dao;

import com.canthny.shardingsphere.springboot.jpa.entity.OrderInfo;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Description：订单dao
 * Created By tanghao on 2019/11/29
 */
//@Repository
public interface OrderInfoDao extends JpaRepository<OrderInfo,Long>, JpaSpecificationExecutor<OrderInfo> {

    List<OrderInfo> findOrderInfosByUserId(Long userId);

    OrderInfo findOrderInfoByOrderNo(String orderNo);

    List<OrderInfo> findOrderInfosByCreatedDateAfter(Date date);
}
