package com.lc.lcserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lcserve.entity.MakCount;
import com.lc.lcserve.vo.R;

/**
 *
 */
public interface MakCountService extends IService<MakCount> {

    R getMakCountInfoById(Integer mid);
}
