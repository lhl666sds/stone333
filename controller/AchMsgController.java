package com.lc.lcserve.controller;

import com.lc.lcserve.entity.Achieves;
import com.lc.lcserve.entity.Atlas;
import com.lc.lcserve.service.AchievesService;
import com.lc.lcserve.service.AtlasService;
import com.lc.lcserve.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/achs")
public class AchMsgController {

    @Autowired
    AtlasService atlasService;

    @Autowired
    AchievesService achievesService;

    @PostMapping("/allatlas")
    public R atlasreq(HttpServletRequest httpServletRequest, Model model){
        List<List<Atlas>> list = atlasService.atlasreq();
        model.addAttribute("atlasreq",list);
        return R.success(list);
    }

    @PostMapping("/allachis")
    public R achisreq(HttpServletRequest httpServletRequest, Model model){
        List<Achieves> list = achievesService.list();
        model.addAttribute("achisreq",list);
        return R.success(list);
    }

    // 添加商品
    @PostMapping("")
    public R insertUser(@RequestBody Atlas atlas) {
        return R.success(atlasService.save(atlas));
    }

    // 删除单个商品
    @DeleteMapping("/{aid}")
    public R deleteUser(@PathVariable(value = "aid") Integer aid) {
        return R.success(atlasService.removeById(aid));
    }

    // 删除多个商品
    @DeleteMapping("")
    public R deleteUserMore(@RequestBody List<Integer> ids) {
        return R.success(atlasService.removeByIds(ids));
    }

    // 编辑用户
    @PutMapping("")
    public R modifyUser(@RequestBody Atlas atlas) {
        return R.success(atlasService.update());
    }
}
