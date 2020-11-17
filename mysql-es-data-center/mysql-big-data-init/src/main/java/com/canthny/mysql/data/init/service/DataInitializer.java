package com.canthny.mysql.data.init.service;

import com.canthny.mysql.data.init.dao.PtOrderGoodsMapper;
import com.canthny.mysql.data.init.dao.PtOrderInfoMapper;
import com.canthny.mysql.data.init.dao.UserInfoMapper;
import com.canthny.mysql.data.init.domain.PtOrderGoods;
import com.canthny.mysql.data.init.domain.PtOrderInfo;
import com.canthny.mysql.data.init.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description： TODO
 * Created By tanghao on 2020/11/10
 */
@Component
public class DataInitializer {

    @Autowired
    PtOrderGoodsMapper ptOrderGoodsMapper;
    @Autowired
    PtOrderInfoMapper ptOrderInfoMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    private final AtomicLong count = new AtomicLong(0);

    @PostConstruct
    public void init(){
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int i=1;i<101;i++){
            DataInitThread thread = new DataInitThread((long)i);
            executorService.execute(thread);
        }
    }

    class DataInitThread implements Runnable{

        private Long count;
        DataInitThread(Long count){
            this.count = count;
        }

        @Override
        public void run() {
            Long id = count;
            while(id<=10000000){
                Long amount = (long)((Math.random()*10000+10));
                UserInfo userInfo = new UserInfo();
                userInfo.setAccountNo("th"+id);
                userInfo.setUserType("1");
                userInfo.setLoginName(id+"@qq.com");
                userInfo.setPass("123123qq");
                userInfo.setStatus(1);
                userInfo.setCreatedTime(new Date());
                userInfo.setModifiedTime(new Date());
                userInfoMapper.insert(userInfo);
                PtOrderInfo ptOrderInfo = new PtOrderInfo();
                ptOrderInfo.setAmount(amount);
                ptOrderInfo.setBuyerAccountNo("th"+id);
                ptOrderInfo.setCreatedDate(new Date());
                ptOrderInfo.setModifiedDate(new Date());
                ptOrderInfo.setOrderNo("20201110"+id);
                ptOrderInfo.setOrderStatus(1);
                ptOrderInfo.setOrderTitle("测试订单"+id);
                ptOrderInfo.setOrderType(1);
                ptOrderInfo.setRemark("订单"+id+"备注");
                ptOrderInfo.setSellerAccountNo(id>100?"seller"+id%100 : "seller"+id);
                ptOrderInfo.setUserId(id);
                ptOrderInfoMapper.insert(ptOrderInfo);
                Random random2 = new Random();
                PtOrderGoods ptOrderGoods1 = new PtOrderGoods();
                ptOrderGoods1.setGoodsCode(String.valueOf((int)(Math.random()*10000+10)));
                ptOrderGoods1.setGoodsNum(random2.nextInt(5)+1);
                ptOrderGoods1.setGoodsPrice(amount/2);
                ptOrderGoods1.setOrderNo(ptOrderInfo.getOrderNo());
                ptOrderGoods1.setRemark("测试订单"+id+"商品一");
                ptOrderGoods1.setCreatedDate(new Date());
                ptOrderGoods1.setModifiedDate(new Date());
                PtOrderGoods ptOrderGoods2 = new PtOrderGoods();
                ptOrderGoods2.setGoodsCode(String.valueOf((int)(Math.random()*10000+10)));
                ptOrderGoods2.setGoodsNum(random2.nextInt(5)+1);
                ptOrderGoods2.setGoodsPrice(amount-amount/2);
                ptOrderGoods2.setOrderNo(ptOrderInfo.getOrderNo());
                ptOrderGoods2.setRemark("测试订单"+id+"商品二");
                ptOrderGoods2.setCreatedDate(new Date());
                ptOrderGoods2.setModifiedDate(new Date());
                ptOrderGoodsMapper.insert(ptOrderGoods1);
                count = count+100;
            }
        }
    }
}
