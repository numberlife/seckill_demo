package com.qjmoneynode.seckilldemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author qjmoneynode
 * @since 2023-07-14
 */
@Data
@TableName("t_seckill_order")
@ApiModel(value = "SeckillOrder对象", description = "")
public class SeckillOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("秒杀订单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("订单ID")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty("商品ID")
    @TableField("goods_id")
    private Long goodsId;


}