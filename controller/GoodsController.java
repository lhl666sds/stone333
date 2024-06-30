package com.lc.lcserve.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lc.lcserve.entity.TransGoods;
import com.lc.lcserve.service.TransGoodsService;
import com.lc.lcserve.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    TransGoodsService transGoodsService;

    @PostMapping("/all")
    public R goodsreq(HttpServletRequest httpServletRequest, Model model){
        List<List<TransGoods>> source = new ArrayList<>();
        List<TransGoods> list1 = transGoodsService.goodsreq("1");
        List<TransGoods> list2 = transGoodsService.goodsreq("2");
        source.add(list1);
        source.add(list2);

        return R.success(source);
    }

    // 添加商品
    @PostMapping("")
    public R insertUser(@RequestBody TransGoods transGoods) {
        return R.success(transGoodsService.save(transGoods));
    }

    // 删除单个商品
    @DeleteMapping("/{gid}")
    public R deleteUser(@PathVariable(value = "gid") Integer gid) {
        return R.success(transGoodsService.removeById(gid));
    }

    // 删除多个商品
    @DeleteMapping("")
    public R deleteUserMore(@RequestBody List<Integer> ids) {
        return R.success(transGoodsService.removeByIds(ids));
    }

    // 编辑用户
    @PutMapping("")
    public R modifyUser(@RequestBody TransGoods transGoods) {
        return R.success(transGoodsService.update());
    }

    // 分页查询
    @GetMapping("/page")
    public R findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<TransGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
//        User currentUser = TokenUtils.getCurrentUser();
//        if (currentUser.getRole().equals("ROLE_USER")) {
//            queryWrapper.eq("user", currentUser.getUsername());
//        }
        return R.success(transGoodsService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
