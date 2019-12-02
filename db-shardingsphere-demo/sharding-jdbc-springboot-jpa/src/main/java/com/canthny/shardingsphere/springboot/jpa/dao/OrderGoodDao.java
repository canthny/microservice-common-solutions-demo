package com.canthny.shardingsphere.springboot.jpa.dao;

import com.canthny.shardingsphere.springboot.jpa.entity.OrderGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Description：订单下商品dao
 * Created By tanghao on 2019/11/29
 */
//@Repository
public interface OrderGoodDao extends JpaRepository<OrderGood,Long>, JpaSpecificationExecutor<OrderGood> {
}
