package com.lc.lcserve.controller;

import com.lc.lcserve.service.TransOrderServicer;
import com.lc.lcserve.vo.R;
import com.lc.lcserve.vo.params.GetOrderParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2023/4/23&0:27
 **/
@RestController
@CrossOrigin
@RequestMapping("/order")
public class TransOrderController {

    @Resource
    private TransOrderServicer transOrderServicer;


    @GetMapping("/buy")
    public R buySomething(@RequestParam(value = "uid") String uid , @RequestParam(value = "oid") String gid , @RequestParam(value = "counts") Integer counts){
        return transOrderServicer.buySomething(uid , gid , counts);
    }

    @GetMapping("/sure")
    public R suerBuy(@RequestParam(value = "uid") Integer uid , @RequestParam(value = "oid") List<Integer> oid ){
        return transOrderServicer.suerBuy(uid , oid);
    }

}
