package com.lc.lcserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lcserve.entity.TransGoods;

import java.util.List;

/**
 *
 */
public interface TransGoodsService extends IService<TransGoods> {

    TransGoods getGoodByGid(Integer gid);

    List<TransGoods> goodsreq(String i);
}
