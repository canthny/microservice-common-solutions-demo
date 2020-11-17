package com.canthny.es.data.design;

import com.alibaba.fastjson.JSONObject;
import com.canthny.es.data.design.domain.TestInfo;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import javax.annotation.Resource;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Description： TODO
 * {"age":18,"id":"1","name":"tanghao","remark":"备注:我怎么这么好看","userLabel":"smart handsome clever genius"}
 * Created By tanghao on 2020/6/9
 */
public class SimpleQueryTest extends BaseTests{

    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ElasticsearchOperations operations;

    @Test
    public void testTerm(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("name","tanghao")).build();
        List<TestInfo> testInfoList = elasticsearchTemplate.queryForList(searchQuery, TestInfo.class);
        System.out.println(JSONObject.toJSONString(testInfoList));
    }

    @Test
    public void testBoolQuery(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.boolQuery()
//                .must(matchQuery("name","t"))
                .must(rangeQuery("age").lte(18))
                //用户查询几个关键词中间有slop（间隔数<=slop）的情况
                .must(matchPhraseQuery("userLabel","smart clever").slop(1))
                //用于即时搜索，标签为smart h开头的结果，并且限制符合条件的按字典排序后的数量
                //如果有smart ha、smart hc、smart hz等标签，maxExpansions是1，则只能查出smart ha和smart hc的两条，hz的查不到
                .must(matchPhrasePrefixQuery("userLabel","smart h").maxExpansions(1))
                .must(multiMatchQuery("clever","name","userLabel"))
        ).withRoute("1").build();
        List<TestInfo> testInfoList = elasticsearchTemplate.queryForList(searchQuery, TestInfo.class);
        testInfoList.forEach((testInfo)->System.out.println(JSONObject.toJSONString(testInfo)));

    }
}
