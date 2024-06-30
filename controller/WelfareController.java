package com.lc.lcserve.controller;

import com.lc.lcserve.entity.WelActs;
import com.lc.lcserve.service.WelActsService;
import com.lc.lcserve.service.WelCountService;
import com.lc.lcserve.vo.R;
import com.lc.lcserve.vo.WelActScheduleVo;
import com.lc.lcserve.vo.params.AddActionParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/wels")
public class WelfareController {

    @Autowired
    WelActsService welActsService;

    @Autowired
    WelCountService welCountService;

    @RequestMapping("/joinacts")
    public R joinActs(@PathVariable(value = "wid") Integer wid){
        return welCountService.joinOrgnization(wid);
    }

    @RequestMapping("/welacts")
    public R welActs(@RequestBody WelActs welActs){
        return welActsService.allWelActs(welActs);
    }

    @PostMapping("/all")
    public R atlasreq(HttpServletRequest httpServletRequest, Model model){
        List<WelActScheduleVo> list = welActsService.welsreq();
        model.addAttribute("welsreq",list);
        return R.success(list);
    }

    @PostMapping("/action/all")
    public R getAllAction(){
        return welActsService.getAllAction();
    }


    // 添加商品
    @PostMapping("")
    public R insertUser(@RequestBody WelActs welActs) {
        return R.success(welActsService.save(welActs));
    }

    // 删除单个商品
    @DeleteMapping("/{aid}")
    public R deleteUser(@PathVariable(value = "wid") Integer wid) {
        return R.success(welActsService.removeById(wid));
    }

    // 删除多个商品
    @DeleteMapping("")
    public R deleteUserMore(@RequestBody List<Integer> ids) {
        return R.success(welActsService.removeByIds(ids));
    }

    // 编辑用户
    @PutMapping("")
    public R modifyUser(@RequestBody WelActs welActs) {
        return R.success(welActsService.update());
    }



    /**
     * 用户参加某一个活动
     * @return
     */
    @PutMapping("/increase")
    public R userAddAction(@RequestParam String uid , @RequestParam String aid) {
        return welActsService.userAddAction(uid,aid);
    }
}
