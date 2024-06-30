package com.lc.lcserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.lcserve.entity.TransGoods;

/**
 * @Entity com.lc.lcserve.entity.TransGoods
 */
public interface TransGoodsMapper extends BaseMapper<TransGoods> {

    int deleteByPrimaryKey(Long id);

    int insert(TransGoods record);

    int insertSelective(TransGoods record);

    TransGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransGoods record);

    int updateByPrimaryKey(TransGoods record);

}
