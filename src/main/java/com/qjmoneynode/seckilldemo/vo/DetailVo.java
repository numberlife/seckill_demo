package com.qjmoneynode.seckilldemo.vo;

import com.qjmoneynode.seckilldemo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qjmoneynode
 * @version 1.0
 * @description 商品详情返回对象
 * @createTime 2023-07-17  11:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVo {

    private User user;

    private GoodsVo goodsVo;

    private int seckillStatus;

    private int remainSeconds;
}
