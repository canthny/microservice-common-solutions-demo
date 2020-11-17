package com.canthny.es.data.design;

import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import javax.annotation.Resource;

/**
 * Description： 聚合查询测试类
 * Created By tanghao on 2020/7/9
 */
public class AggregateTest extends BaseTests{

    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testSum(){

    }
}
