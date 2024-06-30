package com.lc.lcserve.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lcserve.controller.request.CreateOrderRequest;
import com.lc.lcserve.entity.TransGoods;
import com.lc.lcserve.entity.TransOrder;
import com.lc.lcserve.mapper.TransGoodsMapper;
import com.lc.lcserve.mapper.TransOrderMapper;
import com.lc.lcserve.service.TransGoodsService;
import com.lc.lcserve.service.TransOrderService;
import com.lc.lcserve.vo.MarketVO;
import com.lc.lcserve.vo.R;
import com.lc.lcserve.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class TransOrderServiceImpl extends ServiceImpl<TransOrderMapper, TransOrder>
    implements TransOrderService{

    @Resource
    TransOrderMapper transOrderMapper;

    @Resource
    TransGoodsMapper transGoodsMapper;

    @Autowired
    TransGoodsService transGoodsService;

    @Override
    public ResultVO listShoppingCartsByUserId(Integer uid , Integer type) {
        LambdaQueryWrapper<TransOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TransOrder::getUid, uid);
        if(type == 1){
            // 查询其他状态
            queryWrapper.ne(TransOrder::getOstate , "0");
        } else {
            // 查询待支付列表
            queryWrapper.eq(TransOrder::getOstate , "0");
        }
        List<TransOrder> transOrders = transOrderMapper.selectList(queryWrapper);
        ArrayList<MarketVO> voList = new ArrayList<>();
        transOrders.forEach(entity ->{
            MarketVO vo = new MarketVO();
            BeanUtils.copyProperties(entity , vo);
            switch (entity.getOstate()){
                case "0":
                    vo.setOstate("待付款");
                    break;
                case "1":
                    vo.setOstate("待发货");
                    break;
                case "2":
                    vo.setOstate("已发货");
                    break;
                case "3":
                    vo.setOstate("已签收");
                    break;
                default: break;
            }
            vo.setOprice(entity.getOprice());
            voList.add(vo);
        });
        List<MarketVO> marketVOS = getVO(voList);
        return new ResultVO(200, "查询成功", marketVOS);
    }

    @Override
    public ResultVO listShoppingCartsByGids(String gids) {
        //将根据","分隔的cartIds转储在list中
        String[] arr = gids.split(",");
        List<String> list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        List<TransOrder> transOrders = transOrderMapper.selectBatchIds(list);
        List<MarketVO> voList = new ArrayList<>();
        transOrders.forEach(entity ->{
            MarketVO vo = new MarketVO();
            BeanUtils.copyProperties(entity , vo);
            voList.add(vo);
        });
        List<MarketVO> marketVOS = getVO(voList);
        if (marketVOS.size() > 0) {
            return new ResultVO(200, "查询成功", marketVOS);
        }
        return new ResultVO(401, "查询失败", null);
    }

    /**
     * 完善MarketVO的字段
     *
     * @param marketVOS
     * @return
     */
    private List<MarketVO> getVO(List<MarketVO> marketVOS) {
        for (MarketVO marketVO : marketVOS) {
            //查询商品名称
            TransGoods transGoods = transGoodsService.getGoodByGid(marketVO.getGid());
            marketVO.setGname(transGoods.getGname());
            marketVO.setGprice(transGoods.getGprice());
            marketVO.setGamount(transGoods.getGamount());
            //查询商品主照片
            marketVO.setProductImg(transGoods.getGimg());
        }
        return marketVOS;
    }

    @Override
    @Transactional
    public ResultVO addShoppingCart(TransOrder transOrder) {
        Map<String, Object> map = BeanUtil.beanToMap(transOrder);
        MarketVO marketVO = BeanUtil.mapToBean(map, MarketVO.class, false,null);
        int insert = transOrderMapper.insert(transOrder);
        if (insert > 0) {
            return new ResultVO(200, "添加成功", null);
        }
        return new ResultVO(401, "查询失败", null);
    }

    @Override
    @Transactional
    public ResultVO updateCartNum(Integer oid, String onumber) {
        UpdateWrapper<TransOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("onumber",onumber).eq("oid",oid);
        int i = transOrderMapper.update(null, updateWrapper);
        if (i > 0) {
            return new ResultVO(200, "修改成功", null);
        }
        return new ResultVO(401, "修改失败", null);
    }

    @Override
    @Transactional
    public ResultVO removeByCids(String orderIds) {
        //将根据","分隔的cartIds转储在list中
        String[] arr = orderIds.split(",");
        List<String> list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        int i = transOrderMapper.deleteBatchIds(list);
        if (i > 0) {
            return new ResultVO(200, "删除成功", null);
        }
        return new ResultVO(401, "删除失败", null);
    }

    @Override
    public String createOrder(CreateOrderRequest createOrderRequest) {
        return null;
    }

    @Override
    public R buyOrder(TransOrder transOrder) {
        return null;
    }

}




