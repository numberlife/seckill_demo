package com.qjmoneynode.seckilldemo.service.impl;

import com.qjmoneynode.seckilldemo.entity.Goods;
import com.qjmoneynode.seckilldemo.mapper.GoodsMapper;
import com.qjmoneynode.seckilldemo.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qjmoneynode.seckilldemo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qjmoneynode
 * @since 2023-07-14
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }

    /*
     * @description 获取商品详情
     * @author qjmoneynode
     * @param [goodsId]
     * @return java.lang.String
     **/
    @Override
    public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }
}
