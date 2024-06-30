package com.lc.lcserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lcserve.entity.HomeReg;
import com.lc.lcserve.mapper.HomeRegMapper;
import com.lc.lcserve.service.HomeRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class HomeRegServiceImpl extends ServiceImpl<HomeRegMapper, HomeReg>
    implements HomeRegService {

    @Resource
    HomeRegMapper homeRegMapper;

    /**
     * 首页查询返回有限个
     * @return
     */
    @Override
    public List<HomeReg> regulation() {
        LambdaQueryWrapper<HomeReg> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.last("limit 8");
        return homeRegMapper.selectList(queryWrapper);
    }
}




