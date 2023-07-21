package com.qjmoneynode.seckilldemo.service;

import com.qjmoneynode.seckilldemo.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qjmoneynode.seckilldemo.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qjmoneynode
 * @since 2023-07-14
 */
public interface IGoodsService extends IService<Goods> {
    /*
     * @description 获取商品列表
     * @author qjmoneynode
     * @param
     * @return
     **/
    List<GoodsVo> findGoodsVo();

    /*
     * @description 获取商品详情
     * @author qjmoneynode
     * @param [goodsId]
     * @return java.lang.String
     **/
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
