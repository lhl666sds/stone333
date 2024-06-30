package com.lc.lcserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lcserve.entity.WelCount;
import com.lc.lcserve.mapper.WelCountMapper;
import com.lc.lcserve.service.WelCountService;
import com.lc.lcserve.vo.R;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class WelCountServiceImpl extends ServiceImpl<WelCountMapper, WelCount>
    implements WelCountService{

    @Override
    public R getWelCountInfoById(Integer weid) {
        return R.success(baseMapper.selectById(weid));
    }

    @Override
    public R joinOrgnization(Integer wid) {
        WelCount welCount = getById(wid);
        String orgnazation = welCount.getOrganization();
        if (orgnazation != null){
            return R.error().message("您已加入组织");
        }
        return R.success(baseMapper.updateById(welCount));
    }
}




