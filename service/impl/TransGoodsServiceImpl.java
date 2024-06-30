package com.lc.lcserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lcserve.entity.TransGoods;
import com.lc.lcserve.mapper.TransGoodsMapper;
import com.lc.lcserve.service.TransGoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TransGoodsServiceImpl extends ServiceImpl<TransGoodsMapper, TransGoods>
    implements TransGoodsService{

    @Override
    public TransGoods getGoodByGid(Integer gid) {
        return baseMapper.selectById(gid);
    }

    @Override
    public List<TransGoods> goodsreq(String i ) {
        LambdaQueryWrapper<TransGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TransGoods::getGtype , i);
        return baseMapper.selectList(queryWrapper);
    }
}




