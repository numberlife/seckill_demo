package com.qjmoneynode.seckilldemo.vo;

import com.qjmoneynode.seckilldemo.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qjmoneynode
 * @version 1.0
 * @description 订单详情返回对象
 * @createTime 2023-07-17  16:32
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVo {
    private Order order;

    private GoodsVo goodsVo;
}
