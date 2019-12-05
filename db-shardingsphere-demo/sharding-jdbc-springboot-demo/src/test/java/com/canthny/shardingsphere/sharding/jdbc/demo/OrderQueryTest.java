package com.canthny.shardingsphere.sharding.jdbc.demo;

import com.alibaba.fastjson.JSON;
import com.canthny.shardingsphere.springboot.jpa.dao.OrderInfoDao;
import com.canthny.shardingsphere.springboot.jpa.entity.OrderInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;

/**
 * Description： 订单查询测试类
 * Created By tanghao on 2019/12/4
 */
public class OrderQueryTest extends BaseTests{

    @Autowired
    OrderInfoDao orderInfoDao;

    @Test
    public void testQueryOrderInfoByUserId(){
        List<OrderInfo> list = orderInfoDao.findOrderInfosByUserId(79L);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testQueryOrderInfoByOrderNo(){
        OrderInfo orderInfo = orderInfoDao.findOrderInfoByOrderNo("20191205034425000005079");
        System.out.println(JSON.toJSONString(orderInfo));
    }

    @Test
    public void testQueryOrderInfosByCreatedDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019,11,5,15,0,0);
        List<OrderInfo> list = orderInfoDao.findOrderInfosByCreatedDateAfter(calendar.getTime());
        System.out.println(JSON.toJSONString(list));
    }
}
