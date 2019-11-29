package com.canthny.shardingsphere.springboot.jpa.dao;

import com.canthny.shardingsphere.springboot.jpa.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Description：订单dao
 * Created By tanghao on 2019/11/29
 */
public interface OrderInfoDao extends JpaRepository<OrderInfo,Long>, JpaSpecificationExecutor<OrderInfo> {
}
