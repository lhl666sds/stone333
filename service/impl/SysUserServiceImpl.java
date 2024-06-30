package com.lc.lcserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lcserve.entity.SysUser;
import com.lc.lcserve.exception.AppException;
import com.lc.lcserve.exception.AppExceptionCodeMsg;
import com.lc.lcserve.mapper.SysUserMapper;
import com.lc.lcserve.service.SysUserService;
import com.lc.lcserve.tool.PasswordEncoder;
import com.lc.lcserve.vo.R;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Override
    public SysUser login(String username, String password) {
        SysUser user = findByUsername(username);
        // 用户不存在
        if (user == null) {
            throw new AppException(AppExceptionCodeMsg.USER_NOT_EXIST);
        }
        // 与数据库中加密的密码进行校验
        if (!PasswordEncoder.checkPassword(password, user.getPassword())) {
            throw new AppException(AppExceptionCodeMsg.USERNAME_PASSWORD_INCORRECT);
        }
        return user;
    }

    @Override
    public void register(SysUser sysuser) {
        // 校验用户名是否已经被使用
        if (findByUsername(sysuser.getUname())!=null){
            throw new AppException(AppExceptionCodeMsg.USERNAME_IS_EXISTS);
        }
        // 新增用户，并将密码加密
        sysuser.setPassword(PasswordEncoder.hashPassword(sysuser.getPassword()));
        baseMapper.insert(sysuser);
    }

    @Override
    public SysUser findByUsername(String username){
        SysUser user = baseMapper.selectOne(new QueryWrapper<SysUser>().lambda()
                .eq(SysUser::getUname, username));
        return user;
    }

    @Override
    public R getUserInfoById(Integer uid) {
        return R.success(baseMapper.selectById(uid));
    }

    /**
     * 获取用户参与项目和活动的筹备进度
     * @param uid
     * @return
     */
    @Override
    public R getProjectSchedule(Integer uid) {
        return null;
    }
}




