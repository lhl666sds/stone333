package com.lc.lcserve.controller;

import com.lc.lcserve.controller.request.CreateOrderRequest;
import com.lc.lcserve.entity.TransOrder;
import com.lc.lcserve.service.TransGoodsService;
import com.lc.lcserve.service.TransOrderService;
import com.lc.lcserve.vo.R;
import com.lc.lcserve.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/buying")
public class MarketController {
    @Autowired
    TransOrderService transOrderService;

    @Autowired
    TransGoodsService transGoodsService;

    @GetMapping("/list/{uid}")
    public ResultVO alllist(@PathVariable Integer uid,@RequestParam("tpye")Integer type){
        ResultVO resultVO = transOrderService.listShoppingCartsByUserId(uid , type);
        return resultVO;
    }

    @GetMapping("/listbygids")
    public ResultVO listByGids(String gids, @RequestHeader("token")String token){
        ResultVO resultVO = transOrderService.listShoppingCartsByGids(gids);
        return resultVO;
    }

    @PostMapping("/add")
    public ResultVO addShoppingCart(@RequestBody TransOrder transOrder,@RequestHeader("token")String token){
        ResultVO resultVO = transOrderService.addShoppingCart(transOrder);
        return resultVO;
    }

    @PutMapping("/update/{oid}/{onumber}")
    public ResultVO updateNum(@PathVariable("oid") Integer oid,
                              @PathVariable("onumber") String onumber,
                              @RequestHeader("token") String token){
        ResultVO resultVO = transOrderService.updateCartNum(oid, onumber);
        return resultVO;
    }


    @DeleteMapping(value = "/delete/{oid}")
    public ResultVO remove( @PathVariable("oid") String orderIds,
                            @RequestHeader("token") String token) {
        ResultVO resultVO = transOrderService.removeByCids(orderIds);
        return resultVO;
    }

    @PostMapping("/neworder")
    public R creatOrder(@RequestBody CreateOrderRequest createOrderRequest){
        String orderNum = transOrderService.createOrder(createOrderRequest);
        return R.success(orderNum);
    }

    @RequestMapping("/allorder")
    public R buyOrder(@RequestBody TransOrder transOrder, HttpServletRequest request){

        return R.success(transOrderService.buyOrder(transOrder));
    }


}
