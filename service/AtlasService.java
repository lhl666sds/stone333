package com.lc.lcserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lcserve.entity.Atlas;

import java.util.List;

/**
 *
 */
public interface AtlasService extends IService<Atlas> {

    List<List<Atlas>> atlasreq();
}
