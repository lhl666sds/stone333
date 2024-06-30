package com.lc.lcserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lc.lcserve.entity.TransGoods;
import com.lc.lcserve.entity.TransOrder;
import com.lc.lcserve.mapper.TransGoodsMapper;
import com.lc.lcserve.mapper.TransOrderMapper;
import com.lc.lcserve.service.TransOrderServicer;
import com.lc.lcserve.vo.R;
import com.lc.lcserve.vo.params.GetOrderParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @date 2023/4/23&0:29
 **/
@Service
public class TransOrderServicerImpl implements TransOrderServicer {

    @Resource
    private TransOrderMapper transOrderMapper;

    @Resource
    private TransGoodsMapper transGoodsMapper;

    @Override
    public R buySomething(String uid, String gid, Integer counts) {
        TransGoods transGoods = transGoodsMapper.selectById(gid);
        if(transGoods.getGamount() == 0) return R.error("商品数量不足");
        // 商品售出数量减少
        transGoods.setGamount(transGoods.getGamount() - 1);
        transGoodsMapper.updateById(transGoods);
        if(transGoods == null) return R.error();
        TransOrder transOrder = new TransOrder();
        transOrder.setUid(Integer.parseInt(uid));
        transOrder.setGid(Integer.parseInt(gid));
        transOrder.setOnumber(counts.toString());
        transOrder.setOprice(Integer.parseInt(transGoods.getGprice()) * counts);
        transOrder.setOrdtime(new Date());
        transOrder.setOstate("0");
        transOrderMapper.insert(transOrder);

        return R.success();
    }

    /**
     * 确认购买
     * @param uid
     * @param oid
     * @return
     */
    @Override
    public R suerBuy(Integer uid, List<Integer> oid) {
        if(oid.size() == 0) return R.error();
        oid.forEach(
                item ->{
                    TransOrder transOrder = transOrderMapper.selectById(item);
                    // 更新订单状态
                    transOrder.setOstate("1");
                    transOrderMapper.updateById(transOrder);
                }
        );
        return R.success();
    }
}
