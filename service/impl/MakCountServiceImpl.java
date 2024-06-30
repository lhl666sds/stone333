package com.lc.lcserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lcserve.entity.MakCount;
import com.lc.lcserve.mapper.MakCountMapper;
import com.lc.lcserve.service.MakCountService;
import com.lc.lcserve.vo.R;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class MakCountServiceImpl extends ServiceImpl<MakCountMapper, MakCount>
    implements MakCountService {

    @Override
    public R getMakCountInfoById(Integer mid) {
        return R.success(baseMapper.selectById(mid));
    }
}




