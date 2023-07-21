package com.qjmoneynode.seckilldemo.vo;

import com.qjmoneynode.seckilldemo.entity.Goods;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qjmoneynode
 * @version 1.0
 * @description 商品返回对象
 * @createTime 2023-07-15  12:43
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends Goods {
    /**
     * 秒杀价格
     **/
    @ApiModelProperty("秒杀价格")
    private BigDecimal seckillPrice;

    /**
     * 剩余数量
     **/
    @ApiModelProperty("剩余数量")
    private Integer stockCount;

    /**
     * 开始时间
     **/
    @ApiModelProperty("开始时间")
    private Date startDate;

    /**
     * 结束时间
     **/
    @ApiModelProperty("结束时间")
    private Date endDate;
}
