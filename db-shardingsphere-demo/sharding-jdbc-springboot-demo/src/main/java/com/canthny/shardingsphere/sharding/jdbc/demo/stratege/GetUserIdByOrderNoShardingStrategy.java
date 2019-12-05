package com.canthny.shardingsphere.sharding.jdbc.demo.stratege;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * Description：复杂分片策略——根据userId进行分表，若无userId,则根据orderNo去获取userId
 * insert语句和根据用户id查询订单时肯定包含切分键，可以找到指定的库表
 * 当根据订单号查询时条件可能不会带userId，此时就可以根据orderNo去关联userId进行分片查询，尽可能的避免广播查询
 * 本示例使用userId末3位取模分片，orderNo在设计的时候就把userId的末3位放在最后，因此可以直接根据orderNo的末3位进行sharding
 * 若orderNo设计时没有包含userId,那么也可以根据orderNo在缓存或者关联表中获取userId在进行sharding
 * Created By tanghao on 2019/12/4
 */
public class GetUserIdByOrderNoShardingStrategy implements ComplexKeysShardingAlgorithm<Comparable<?>> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());

        Map<String, Collection<Comparable<?>>> map = shardingValue.getColumnNameAndShardingValuesMap();
        for(String tableName:availableTargetNames){
            Collection<Comparable<?>> userIds = map.get("user_id");
            if(CollectionUtils.isNotEmpty(userIds)){
                Iterator<Comparable<?>> iterator = userIds.iterator();
                while(iterator.hasNext()){
                    Comparable<?> temp = iterator.next();
                    String userId = String.valueOf(temp);
                    String uidSubStr = userId.length() <= 3 ? userId : userId.substring(userId.length()-3);
                    if(tableName.endsWith(Integer.parseInt(uidSubStr) % 2 + "")){
                        result.add(tableName);
                    }
                }
            }else{
                Collection<Comparable<?>> orderNos = map.get("order_no");
                Iterator<Comparable<?>> iterator = orderNos.iterator();
                while(iterator.hasNext()){
                    Comparable<?> temp = iterator.next();
                    String orderNo = (String)temp;
                    String uidSubStr = orderNo.length() <= 3 ? orderNo : orderNo.substring(orderNo.length()-3,orderNo.length());
                    if(tableName.endsWith(Integer.parseInt(uidSubStr) % 2 + "")){
                        result.add(tableName);
                    }
                }
            }
        }
        return result;
    }
}
