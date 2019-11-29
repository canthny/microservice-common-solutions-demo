package com.canthny.shardingsphere.sharding.jdbc.demo.controllers;

import com.canthny.shardingsphere.springboot.jpa.dao.OrderGoodDao;
import com.canthny.shardingsphere.springboot.jpa.dao.OrderInfoDao;
import com.canthny.shardingsphere.springboot.jpa.entity.OrderGood;
import com.canthny.shardingsphere.springboot.jpa.entity.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Description： TODO
 * Created By tanghao on 2019/11/29
 */
@RestController
public class TestController {

    private String format = "yyyyMMddhhmiss";

    @Autowired
    OrderInfoDao orderInfoDao;

    @Autowired
    OrderGoodDao orderGoodDao;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
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
        return "success";
    }

    private String getOrderNo(int i){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String time = sdf.format(new Date());
        return new StringBuilder(time).append(String.format("%06d",i)).toString();
    }
}
