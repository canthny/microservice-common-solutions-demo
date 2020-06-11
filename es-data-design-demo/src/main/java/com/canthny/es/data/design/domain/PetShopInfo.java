package com.canthny.es.data.design.domain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * Description： 宠物店
 * Created By tanghao on 2020/6/11
 */
@Data
@Document(indexName = "pet_shop",type = "pet_shop_all")
public class PetShopInfo {

    @Field(name = "pet_shop_id")
    private String petShopId;

    @Field(name = "pet_shop_name")
    private String petShopName;

    @Field(name = "pets", type = FieldType.Nested)
    private List<PetInfo> pets;
}
