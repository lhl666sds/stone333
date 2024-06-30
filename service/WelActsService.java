package com.lc.lcserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lcserve.entity.WelActs;
import com.lc.lcserve.vo.R;
import com.lc.lcserve.vo.WelActScheduleVo;
import com.lc.lcserve.vo.params.AddActionParams;

import java.util.List;

/**
 *
 */
public interface WelActsService extends IService<WelActs> {

    R allWelActs(WelActs welActs);

    List<WelActScheduleVo> welsreq();

    R userAddAction(String uid , String aid);

    R getAllAction();
}
