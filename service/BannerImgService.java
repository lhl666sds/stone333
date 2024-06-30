package com.lc.lcserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lcserve.entity.BannerImg;
import com.lc.lcserve.vo.R;

import java.util.List;

/**
 *
 */
public interface BannerImgService extends IService<BannerImg> {

    List<BannerImg> homeimg();

    R getAllImg();

}
