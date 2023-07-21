package com.qjmoneynode.seckilldemo.mapper;

import com.qjmoneynode.seckilldemo.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qjmoneynode.seckilldemo.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qjmoneynode
 * @since 2023-07-14
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
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
