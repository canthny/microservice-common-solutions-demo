package com.canthny.es.data.design.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * Description： TODO
 * Created By tanghao on 2020/6/11
 */
@Data
public class Pet {

    @JsonProperty(value = "pet_id")
    private String petId;
    @JsonProperty(value = "pet_name")
    private String petName;
    @JsonProperty(value = "pet_price")
    private String petPrice;
    @JsonProperty(value = "pet_labels")
    private List<String> petLabels;
    @JsonProperty(value = "pet_user_relations")
    @Field(type = FieldType.Nested)
    private List<PetUserRelation> petUserRelations;
}
