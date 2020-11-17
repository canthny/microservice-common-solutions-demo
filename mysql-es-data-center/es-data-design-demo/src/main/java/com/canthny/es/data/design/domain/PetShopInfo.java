package com.canthny.es.data.design.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(value = "pet_shop_id")
    private String petShopId;

    @JsonProperty(value = "pet_shop_name")
    private String petShopName;

    @Field( type = FieldType.Nested)
    private List<Pet> pets;
}
