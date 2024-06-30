package com.lc.lcserve.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lcserve.entity.*;
import com.lc.lcserve.exception.AppException;
import com.lc.lcserve.exception.AppExceptionCodeMsg;
import com.lc.lcserve.mapper.*;
import com.lc.lcserve.service.AdminService;
import com.lc.lcserve.tool.PasswordEncoder;
import com.lc.lcserve.vo.*;
import com.lc.lcserve.vo.params.LogParams;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 *
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

    @Resource
    AdminMapper adminMapper;

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    private LogMapper logMapper;

    @Resource
    private TransOrderMapper transOrderMapper;

    @Resource
    private TransGoodsMapper transGoodsMapper;

    @Resource
    private AtlasMapper atlasMapper;

    private static final SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public void register(SysUser sysuser) {
        // 校验用户名是否已经被使用
        if (findByUsername(sysuser.getUname()) == null) {
            // 新增用户，并将密码加密
            sysuser.setPassword(PasswordEncoder.hashPassword(sysuser.getPassword()));
            sysuser.setBalance("0");
            sysuser.setIncreaceNumber(2);
            sysUserMapper.insert(sysuser);
        }
    }

    @Override
    public Admin findByAdminname(String adminname){
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().lambda()
                .eq(Admin::getAdmin, adminname));
        return admin;
    }

    @Override
    public Admin login(String adminname, String password) {
        Admin admin = findByAdminname(adminname);
        // 与数据库中加密的密码进行校验
        if (!PasswordEncoder.checkPassword(password, admin.getPassword())) {
            throw new AppException(AppExceptionCodeMsg.USERNAME_PASSWORD_INCORRECT);
        }
        return admin;
    }

    @Override
    public SysUser findByUsername(String username){
        SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda()
                .eq(SysUser::getUname, username));
        return user;
    }

    /**
     * 查询用户逻辑
     * @return
     */
    @Override
    public Object searchUser() {
        return sysUserMapper.selectList(null);
    }

    @Override
    public Result<Page<UserLogVo>> getUserLoginLogByLimit(LogParams searchLogParams) throws ParseException {
        // 按照日志查询用户登录日志
        LambdaQueryWrapper<Log> queryWrapper = new LambdaQueryWrapper<>();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(!"".equals(searchLogParams.getBeginTime()) && !"".equals(searchLogParams.getEndTime())){
            Date beginM = formatter.parse(searchLogParams.getBeginTime());
            Date endM = formatter.parse(searchLogParams.getEndTime());
            log.error("开始时间{}" , beginM);
            log.error("结束时间{}" , endM);
            queryWrapper.between(Log::getHappenTime,beginM.getTime(),endM.getTime());
        }
        if(searchLogParams.getStatus() != null && !"".equals(searchLogParams.getStatus())){
            queryWrapper.eq(Log::getStatus , searchLogParams.getStatus());
        }
        // sql
        queryWrapper.eq(Log::getDescription,"login");
        Integer counts = logMapper.selectCount(queryWrapper);
        Page<UserLogVo> page = new Page<>(Integer.parseInt(searchLogParams.getRequestPage()) , Integer.parseInt(searchLogParams.getPages()) , counts,(int)Math.ceil(counts/(Integer.parseInt(searchLogParams.getPages())*1.0)));
        // 分页
        queryWrapper.last("limit " + searchLogParams.getRequestPage() +" , " + searchLogParams.getPages());
        List<Log> rsglLogs = logMapper.selectList(queryWrapper);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        List<UserLogVo> source = new ArrayList<>();
        for (Log rsglLog : rsglLogs) {
            UserLogVo login = new UserLogVo();
            BeanUtils.copyProperties(rsglLog , login);
            login.setHappenTime(formatter.format(rsglLog.getHappenTime()));
            source.add(login);
        }
        page.setDataList(
                source
        );
        return Result.success(page);
    }

    @Override
    public Result changeGoodInfos(TransGoods transGoods) {
        transGoodsMapper.updateById(transGoods);
        return Result.success();
    }

    /**
     * 搜索商品模块信息
     * @return
     */
    @Override
    public Result searchGoodInfo() {
        List<TransGoods> transGoods = transGoodsMapper.selectList(null);
        transGoods.forEach(
                item -> {
                    item.setGtype("1".equals(item.getGtype()) ? "虚拟商品" : "实物商品");
                }
        );
        return Result.success(transGoods);
    }

    /**
     * 交易统计模块
     * @return
     */
    @Override
    public Result seachOrderInfo() {
        // todo 这里看前端需要什么类型的数据  =》 echars 表格选择  饼图 or 柱状图
        return null;
    }

    /**
     * 删除商品的接口
     * @param gid
     * @return
     */
    @Override
    public Result deleteGoodsById(String gid) {
        transGoodsMapper.deleteById(gid);
        return Result.success();
    }

    @Override
    public Result searchAtlasInfos() {
        List<Atlas> atlases = atlasMapper.selectList(null);
        atlases.forEach(
                item -> {
                    switch (item.getType()){
                        case "1" :
                            item.setType("固碳产氧");
                            break;
                        case "2" :
                            item.setType("保持水土");
                            break;
                        case "3" :
                            item.setType("防风固沙");
                            break;
                        case "4" :
                            item.setType("濒危动物");
                            break;
                        default: break;
                    }
                }
        );
        return Result.success(atlases);
    }

    @Override
    public Result changeAtlasInfo(Atlas atlas) {
        atlasMapper.updateById(atlas);
        return Result.success();
    }

    /**
     * char图表数据获取
     * @return
     */
    @Override
    public Result getChartsInfo() {
        List<ChartVo> chartVos = transOrderMapper.selectChars();
        ChartsMemberVo<String> legend = new ChartsMemberVo<>(new String[]{"销量"});
        ChartsMemberVo<String> xAxis = new ChartsMemberVo<>(chartVos.stream().map(ChartVo::getGname).collect(Collectors.toList()).toArray(new String[chartVos.size()]));
        ChartsMemberVo<Integer> series = new ChartsMemberVo<Integer>(
                chartVos.stream().map(ChartVo::getNumber).collect(Collectors.toList()).toArray(new Integer[chartVos.size()]),
                "bar",
                "销量"
                );
        EchartsInfoVo souce = new EchartsInfoVo(legend , xAxis , series);
        return Result.success(souce);
    }

    @Override
    public Object changeUserInfo(SysUser sysUser) {
        SysUser orignUser = sysUserMapper.selectById(sysUser.getUid());
        orignUser.setUname(sysUser.getUname() != null && !"".equals(sysUser.getUname()) ? sysUser.getUname() : orignUser.getUname());
        orignUser.setPhone(sysUser.getPhone());
        orignUser.setNickname(sysUser.getNickname());
        orignUser.setPassword(PasswordEncoder.hashPassword(sysUser.getPassword()));
        sysUserMapper.updateById(orignUser);
        return "success";
    }


}




