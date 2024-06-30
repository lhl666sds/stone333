package com.lc.lcserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.lcserve.entity.SysUser;

/**
 * @Entity com.lc.lcserve.entity.SysUser
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

}
