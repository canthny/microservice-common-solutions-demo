package com.canthny.es.data.design.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Description： TODO
 * Created By tanghao on 2020/6/11
 */
@Data
public class PetUserRelation {

    @JsonProperty(value = "user_id")
    private String userId;
    @JsonProperty(value = "user_name")
    private String userName;
    @JsonProperty(value = "user_phone")
    private String userPhone;
}
