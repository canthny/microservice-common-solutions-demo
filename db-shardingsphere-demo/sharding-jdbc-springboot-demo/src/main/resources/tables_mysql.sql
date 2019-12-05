create table pt_order_info_0
(
    id bigint auto_increment comment '主键'
        primary key,
    user_id bigint not null comment '用户标识id',
    order_no varchar(64) not null comment '交易单号',
    buyer_account_no varchar(64) null comment '买家账号',
    seller_account_no varchar(64) null comment '卖家账号',
    amount bigint not null comment '订单金额',
    order_type int(2) not null comment '订单类型',
    order_status int(2) not null comment '订单状态',
    order_title varchar(64) null comment '订单名称',
    remark varchar(128) null comment '备注',
    created_date datetime not null comment '创建时间(领取时间)',
    modified_date datetime not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    DEFAULT CHARSET=utf8mb4 comment '平台订单表';

create index idx_order_info_no_0
    on pt_order_info_0 (order_no);

create index idx_order_info_user_0
    on pt_order_info_0 (user_id);


create table pt_order_info_1
(
    id bigint auto_increment comment '主键'
        primary key,
    user_id bigint not null comment '用户标识id',
    order_no varchar(64) not null comment '交易单号',
    buyer_account_no varchar(64) not null comment '买家账号',
    seller_account_no varchar(64) not null comment '卖家账号',
    amount bigint not null comment '订单金额',
    order_type int(2) not null comment '订单类型',
    order_status int(2) not null comment '订单状态',
    order_title varchar(64) null comment '订单名称',
    remark varchar(128) null comment '备注',
    created_date datetime not null comment '创建时间(领取时间)',
    modified_date datetime not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    DEFAULT CHARSET=utf8mb4 comment '平台订单表';

create index idx_order_info_no_1
    on pt_order_info_1 (order_no);

create index idx_order_info_user_1
    on pt_order_info_1 (user_id);


create table pt_order_goods_0
(
    id bigint auto_increment comment '主键'
        primary key,
    order_no varchar(64) not null comment '交易单号',
    goods_code varchar(64) null comment '商品编码',
    goods_num int not null comment '商品数量',
    goods_price bigint not null comment '商品价格',
    remark varchar(128) null comment '备注',
    created_date datetime not null comment '创建时间(领取时间)',
    modified_date datetime not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    DEFAULT CHARSET=utf8mb4 comment '订单商品表';

create index idx_order_goods_no_0
    on pt_order_goods_0 (order_no);

create table pt_order_goods_1
(
    id bigint auto_increment comment '主键'
        primary key,
    order_no varchar(64) not null comment '交易单号',
    goods_code varchar(64) null comment '商品编码',
    goods_num int not null comment '商品数量',
    goods_price bigint not null comment '商品价格',
    remark varchar(128) null comment '备注',
    created_date datetime not null comment '创建时间(领取时间)',
    modified_date datetime not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    DEFAULT CHARSET=utf8mb4 comment '订单商品表';

create index idx_order_goods_no_1
    on pt_order_goods_1 (order_no);



drop table pt_order_info_0;
drop table pt_order_info_1;
drop table pt_order_goods_0;
drop table pt_order_goods_1;

