package com.lc.lcserve.service;

import com.lc.lcserve.vo.R;

import java.util.List;

/**
 * @date 2023/4/23&0:28
 **/
public interface TransOrderServicer {
    R buySomething(String uid, String gid, Integer counts);

    R suerBuy(Integer uid, List<Integer> oid);
}
