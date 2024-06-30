package com.lc.lcserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lcserve.entity.Atlas;
import com.lc.lcserve.mapper.AtlasMapper;
import com.lc.lcserve.service.AtlasService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class AtlasServiceImpl extends ServiceImpl<AtlasMapper, Atlas>
    implements AtlasService {

    @Override
    public List<List<Atlas>> atlasreq() {
        List<List<Atlas>> source = new ArrayList<>();
        for(int i = 1; i <= 4 ; i++){
            LambdaQueryWrapper<Atlas> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Atlas::getType, i);
            source.add(baseMapper.selectList(queryWrapper));
        }
        return source;
    }
}




