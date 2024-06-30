package com.lc.lcserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lcserve.entity.WelCount;
import com.lc.lcserve.vo.R;

/**
 *
 */
public interface WelCountService extends IService<WelCount> {

    R getWelCountInfoById(Integer weid);

    R joinOrgnization(Integer wid);
}
