package com.canthny.es.data.design.domain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * Description： TODO
 * Created By tanghao on 2020/6/11
 */
@Data
public class PetUserInfo {

    @Field(name = "user_id")
    private String userId;
    @Field(name = "user_name")
    private String userName;
    @Field(name = "user_phone")
    private String userPhone;
}
