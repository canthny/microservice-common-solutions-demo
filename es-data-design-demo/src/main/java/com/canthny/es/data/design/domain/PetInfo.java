package com.canthny.es.data.design.domain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * Description： TODO
 * Created By tanghao on 2020/6/11
 */
@Data
public class PetInfo {

    private String petId;
    private String petName;
    private List<String> petLabels;

    @Field(name = "pet_user_relations",type = FieldType.Nested)
    private List<PetUserInfo> petUserRelations;
}