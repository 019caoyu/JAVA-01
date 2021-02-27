CREATE TABLE `u_user` (
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`user_name` varchar(20) NOT NULL COMMENT '用户名',
	`user_number` varchar(40) NOT NULL COMMENT '用户唯一标识号码',
	`user_age` tinyint unsigned NOT NULL COMMENT  '用户年龄',
	`user_sex` ENUM('m','f') NOT NULL COMMENT '用户性别',
	`birthday` date NOT NULL COMMENT '用户生日',
	`user_tel` varchar(20) NOT NULL COMMENT '用户电话',
	PRIMARY KEY `user_primary_key`(`id`),
	UNIQUE KEY `uniq_user_number`(`user_number`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


CREATE TABLE `product` (
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`prd_name` varchar(20) NOT NULL COMMENT '商品名',
	`prd_description` varchar(1024) COMMENT '商品描述',
	`price` decimal(19,4) NOT NULL COMMENT '商品价格',
	PRIMARY KEY `prd_primary_key`(`id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

CREATE TABLE `o_order` (
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`ord_number` varchar(64) NOT NULL COMMENT '订单唯一标识号',
	`ord_description` varchar(1024) COMMENT '订单描述',
	`ord_user_id` bigint(20) unsigned  COMMENT '订单用户ID',
	`ord_user_name` varchar(20) NOT NULL COMMENT '订单用户名',
	PRIMARY KEY `prd_primary_key`(`id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
ALTER TABLE `o_order` ADD FOREIGN KEY order_user_fk (`ord_user_id`) REFERENCES u_user (`id`);

CREATE TABLE `o_order_item` (
	`ord_id` bigint(20) unsigned NOT NULL COMMENT '订单ID',
	`prd_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
	PRIMARY KEY `ord_prd_primary_key`(`ord_id`, `prd_id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品关联表';
ALTER TABLE `o_order_item` ADD FOREIGN KEY order_item_order_fk (`ord_id`) REFERENCES o_order (`id`);
ALTER TABLE `o_order_item` ADD FOREIGN KEY order_item_product_fk (`prd_id`) REFERENCES product (`id`);