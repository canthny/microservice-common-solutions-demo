package com.canthny.shardingsphere.sharding.jdbc.demo.stratege;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * Description： 根据订单的创建日期来获取年份进行分表，这里只是举个复杂分片的例子，实现ComplexKeysShardingAlgorithm接口，通常订单表中会有业务日期字段，直接匹配字符串表达式即可
 * Created By canthny on 2019/12/3
 */
public class CreatedDateShardingDbStrategy implements ComplexKeysShardingAlgorithm<Date> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Date> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());

        Map<String, Collection<Date>> map = shardingValue.getColumnNameAndShardingValuesMap();
        for(String dbName:availableTargetNames){
            Collection<Date> collections = map.get("created_date");
            if(CollectionUtils.isNotEmpty(collections)){
                Iterator iterator = collections.iterator();
                if(iterator.hasNext()){
                    Date createDate = (Date)iterator.next();
                    if(dbName.endsWith(getYear(createDate))){
                        result.add(dbName);
                    }
                }
            }
        }
        return result;
    }

    private String getYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

}
