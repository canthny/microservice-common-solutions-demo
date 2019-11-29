package com.canthny.shardingsphere.springboot.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 公用基础字段
 * @author bingbing
 * @create 2017/9/6 16:39
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -8328552264165070919L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    private Date createdDate;

    private Date modifiedDate;
}

