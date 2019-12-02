package com.canthny.shardingsphere.sharding.jdbc.demo;

import com.canthny.shardingsphere.springboot.jpa.dao.OrderGoodDao;
import com.canthny.shardingsphere.springboot.jpa.dao.OrderInfoDao;
import com.canthny.shardingsphere.springboot.jpa.entity.OrderGood;
import com.canthny.shardingsphere.springboot.jpa.entity.OrderInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Description： 订单测试类
 * Created By tanghao on 2019/11/29
 */
public class OrderTest extends BaseTests{

    private String format = "yyyyMMddhhmmss";

    @Autowired
    OrderInfoDao orderInfoDao;

    @Autowired
    OrderGoodDao orderGoodDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testInsertOrderInfo(){
        Random random = new Random(1000);
        for(int i = 1;i<4;i++){
            String orderNo = getOrderNo(i);
            Long amount = 0L;
            for(int j=0;j<2;j++){
                OrderGood orderGood = new OrderGood();
                orderGood.setGoodsCode("1001");
                orderGood.setGoodsNum(1);
                Long goodPrice = random.nextLong();
                orderGood.setGoodsPrice(goodPrice);
                orderGood.setOrderNo(orderNo);
                orderGood.setRemark("商品|"+i+"|"+j);
                orderGood.setCreatedDate(new Date());
                orderGood.setModifiedDate(new Date());
                orderGoodDao.save(orderGood);
                amount+=goodPrice;
            }
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setAmount(amount);
            orderInfo.setBuyerAccountNo("canthnyq8");
            orderInfo.setCreatedDate(new Date());
            orderInfo.setModifiedDate(new Date());
            orderInfo.setOrderNo(orderNo);
            orderInfo.setOrderStatus(1);
            orderInfo.setOrderTitle("随机订单"+i);
            orderInfo.setOrderType(1);
            orderInfo.setRemark("备注一下");
            orderInfo.setSellerAccountNo("JDJR");
            orderInfoDao.save(orderInfo);
        }
    }

    private String getOrderNo(int i){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String time = sdf.format(new Date());
        return new StringBuilder(time).append(String.format("%06d",i)).toString();
    }
}
