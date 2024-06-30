package com.lc.lcserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.lcserve.entity.*;
import com.lc.lcserve.vo.Page;
import com.lc.lcserve.vo.Result;
import com.lc.lcserve.vo.UserLogVo;
import com.lc.lcserve.vo.params.LogParams;

import java.text.ParseException;

/**
 *
 */
public interface AdminService extends IService<Admin> {

    void register(SysUser admin);

    Admin findByAdminname(String adminname);

    Admin login(String adminname,String password);

    SysUser findByUsername(String username);

    Object searchUser();

    Result<Page<UserLogVo>> getUserLoginLogByLimit(LogParams logParams) throws ParseException;

    Result changeGoodInfos(TransGoods transGoods);

    Result searchGoodInfo();

    Result seachOrderInfo();

    Result deleteGoodsById(String gid);

    Result searchAtlasInfos();

    Result changeAtlasInfo(Atlas atlas);

    Result getChartsInfo();

    Object changeUserInfo(SysUser sysUser);
}
