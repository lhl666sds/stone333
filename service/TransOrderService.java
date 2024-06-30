package com.lc.lcserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lcserve.controller.request.CreateOrderRequest;
import com.lc.lcserve.entity.TransOrder;
import com.lc.lcserve.vo.ResultVO;

/**
 *
 */
public interface TransOrderService extends IService<TransOrder> {

    /**
     * 根据userId查询用户购物车信息（商品名称 图片）
     * @param userId
     * @return
     */
    ResultVO listShoppingCartsByUserId(Integer uid , Integer type);

    /**
     * 根据用户id添加购物车
     * @param shoppingCart
     * @return
     */
    ResultVO addShoppingCart(TransOrder transOrder);

    /**
     * 修改购物车数量
     * @param cartId
     * @param cartNum
     * @return
     */
    ResultVO updateCartNum(Integer oid,String onumber);

    /**
     * 根据cartIds删除购物车（多个购物车id用，分隔）
     * @param cartIds
     * @return
     */
    ResultVO removeByCids(String cartIds);

    String createOrder(CreateOrderRequest createOrderRequest);

    Object buyOrder(TransOrder transOrder);

    ResultVO listShoppingCartsByGids(String gids);
}
