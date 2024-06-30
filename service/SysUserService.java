package com.lc.lcserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lcserve.entity.SysUser;
import com.lc.lcserve.vo.R;

/**
 *
 */
public interface SysUserService extends IService<SysUser> {

    SysUser login(String uname, String password);

    void register(SysUser sysUser);

    SysUser findByUsername(String username);

    R getUserInfoById(Integer uid);

    R getProjectSchedule(Integer uid);
}
