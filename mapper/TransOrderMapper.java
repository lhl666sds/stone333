package com.lc.lcserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.lcserve.entity.TransOrder;
import com.lc.lcserve.vo.ChartVo;
import com.lc.lcserve.vo.EchartsInfoVo;

import java.util.List;

/**
 * @Entity com.lc.lcserve.entity.TransOrder
 */
public interface TransOrderMapper extends BaseMapper<TransOrder> {

    int deleteByPrimaryKey(Long id);

//    int insert(TransOrder record);

    int insertSelective(TransOrder record);

    TransOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransOrder record);

    int updateByPrimaryKey(TransOrder record);

    List<ChartVo> selectChars();


}
