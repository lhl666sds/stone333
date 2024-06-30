package com.lc.lcserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lcserve.entity.HomeReg;

import java.util.List;

/**
 *
 */
public interface HomeRegService extends IService<HomeReg> {

    List<HomeReg> regulation();
}
