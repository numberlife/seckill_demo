package com.qjmoneynode.seckilldemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@TableName("t_seckill_goods")
@ApiModel(value = "SeckillGoods对象", description = "")
public class SeckillGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("goods_id")
    private Long goodsId;

    @TableField("seckill_price")
    private BigDecimal seckillPrice;

    @TableField("stock_count")
    private Integer stockCount;

    @TableField("start_date")
    private LocalDateTime startDate;

    @TableField("end_date")
    private LocalDateTime endDate;


}