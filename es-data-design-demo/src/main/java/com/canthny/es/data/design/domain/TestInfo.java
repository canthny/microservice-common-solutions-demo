package com.canthny.es.data.design.domain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description： TODO
 * Created By tanghao on 2020/6/10
 */
@Data
@Document(indexName="canthny-test")
public class TestInfo {

    private String name;
    private String id;
    private String remark;
    private String userLabel;
    private int age;
}
