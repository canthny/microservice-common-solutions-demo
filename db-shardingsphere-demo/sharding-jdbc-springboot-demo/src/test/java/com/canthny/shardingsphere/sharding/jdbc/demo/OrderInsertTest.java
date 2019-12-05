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
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Description： 订单创建测试类
 * Created By tanghao on 2019/11/29
 */
public class OrderInsertTest extends BaseTests{

    private String format = "yyyyMMddhhmmss";

    @Autowired
    OrderInfoDao orderInfoDao;

    @Autowired
    OrderGoodDao orderGoodDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testInsertOrderInfo(){
        Random random = new Random();
        for(int i = 1;i<10;i++){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            long timeMillis = calendar.getTimeInMillis();
            if(timeMillis%2==0){
                calendar.add(Calendar.YEAR,-1);
            }
            Date createDate = calendar.getTime();
            Long userId = (long)random.nextInt(100);
            String orderNo = getOrderNo(i,userId);
            Long amount = 0L;
            for(int j=0;j<2;j++){
                OrderGood orderGood = new OrderGood();
                orderGood.setGoodsCode("1001");
                orderGood.setGoodsNum(1);
                Long goodPrice = (long)random.nextInt(1000);
                orderGood.setGoodsPrice(goodPrice);
                orderGood.setOrderNo(orderNo);
                orderGood.setRemark("商品|"+i+"|"+j);
                orderGood.setCreatedDate(createDate);
                orderGood.setModifiedDate(new Date());
                orderGoodDao.save(orderGood);
                amount+=goodPrice;
            }
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setUserId(userId);
            orderInfo.setAmount(amount);
            orderInfo.setBuyerAccountNo("canthnyq8");
            orderInfo.setCreatedDate(createDate);
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

    private String getOrderNo(int i,Long uid){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String time = sdf.format(new Date());
        String userId = String.valueOf(uid);
        String uidSubStr = userId.length() <= 3 ? userId : userId.substring(userId.length()-3,userId.length());
        return new StringBuilder(time).append(String.format("%06d",i)).append(String.format("%03d",Integer.parseInt(uidSubStr))).toString();
    }
}
