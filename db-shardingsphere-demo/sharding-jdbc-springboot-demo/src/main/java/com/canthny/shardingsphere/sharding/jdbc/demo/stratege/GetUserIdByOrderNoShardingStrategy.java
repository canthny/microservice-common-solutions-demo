package com.canthny.shardingsphere.sharding.jdbc.demo.stratege;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * Description： TODO
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
