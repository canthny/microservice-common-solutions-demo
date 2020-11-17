package com.canthny.es.data.design;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.canthny.es.data.design.domain.PetShopInfo;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import javax.annotation.Resource;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Description： TODO
 * Created By tanghao on 2020/6/9
 */
public class NestedQueryTest extends BaseTests{

    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testMatch(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(
                boolQuery()
                .must(QueryBuilders.nestedQuery("pets.pet_user_relations",
                        boolQuery()
                        .must(matchQuery("pets.pet_user_relations.user_id","001"))
                        .must(matchQuery("pets.pet_user_relations.user_name.keyword","主人一")), ScoreMode.None))
                .must(matchQuery("pet_shop_name","歹"))
                .must(QueryBuilders.nestedQuery("pets",
                        boolQuery().must(rangeQuery("pets.pet_price").lte(9)), ScoreMode.None))
        ).build();
        List<PetShopInfo> petShopInfos = elasticsearchTemplate.queryForList(searchQuery, PetShopInfo.class);
        petShopInfos.forEach((petShopInfo)->System.out.println(JSONObject.toJSONString(petShopInfo)));
    }

}
