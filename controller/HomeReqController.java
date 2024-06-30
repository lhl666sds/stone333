package com.lc.lcserve.controller;


import com.lc.lcserve.entity.BannerImg;
import com.lc.lcserve.entity.HomeReg;
import com.lc.lcserve.service.BannerImgService;
import com.lc.lcserve.service.HomeRegService;
import com.lc.lcserve.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/homeReq")
public class HomeReqController {

    @Autowired
    HomeRegService homeRegService;

    @Autowired
    BannerImgService bannerImgService;

    //获取资讯
    @PostMapping("/regulation")
    public R regulation(Model model) {
        List<HomeReg> list = homeRegService.regulation();
        model.addAttribute("regulation",list);
        return R.success(list);
    }

    //获取轮播图
    @PostMapping("/hrequest")
    public R homeimg(Model model) {
        List<BannerImg> list = bannerImgService.homeimg();
        model.addAttribute("hbanner",list);
        return R.success(list);
    }

}