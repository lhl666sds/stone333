package com.lc.lcserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lcserve.entity.BannerImg;
import com.lc.lcserve.mapper.BannerImgMapper;
import com.lc.lcserve.service.BannerImgService;
import com.lc.lcserve.vo.R;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class BannerImgServiceImpl extends ServiceImpl<BannerImgMapper, BannerImg>
    implements BannerImgService{

    @Override
    public List<BannerImg> homeimg() {
        return baseMapper.selectList(null);
    }

    @Override
    public R getAllImg() {
        QueryWrapper<BannerImg> wrapper = new QueryWrapper<>();
        wrapper.eq("img_type", 1);
        wrapper.orderByAsc("bid");
        List<BannerImg> indexImgs = baseMapper.selectList(wrapper);
        if (indexImgs.size() > 0) {
            // 这里返回图片地址
            return R.success(indexImgs);
        }
        return R.error();
    }

}




