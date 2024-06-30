package com.lc.lcserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.lcserve.entity.Project;
import com.lc.lcserve.entity.SysUser;
import com.lc.lcserve.entity.WelActs;
import com.lc.lcserve.mapper.ProjectMapper;
import com.lc.lcserve.mapper.SysUserMapper;
import com.lc.lcserve.mapper.WelActsMapper;
import com.lc.lcserve.service.WelActsService;
import com.lc.lcserve.vo.R;
import com.lc.lcserve.vo.WelActScheduleVo;
import com.lc.lcserve.vo.params.AddActionParams;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
//import sun.dc.pr.PRError;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 */
@Service
public class WelActsServiceImpl extends ServiceImpl<WelActsMapper, WelActs>
    implements WelActsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private WelActsMapper welActsMapper;

    @Override
    public R allWelActs(WelActs welActs) {
        return R.success(baseMapper.selectList(null));
    }

    @Override
    public List<WelActScheduleVo> welsreq() {
        // todo 这里添加筹备进度
        List<WelActs> welActs = welActsMapper.selectList(null);
        List<WelActScheduleVo> source = new ArrayList<>();
        welActs.forEach(
                item -> {
                    WelActScheduleVo welActScheduleVo = new WelActScheduleVo();
                    BeanUtils.copyProperties(item , welActScheduleVo);
                    float schedule = (float) item.getUids().split(",").length / Integer.parseInt(item.getNumber());
                    DecimalFormat df = new DecimalFormat("0.00");
                    String format = df.format(schedule);
                    Project project = projectMapper.selectById(item.getPid());
                    welActScheduleVo.setSchedule(format.substring(2));
                    welActScheduleVo.setProjectName(project.getName());
                    source.add(welActScheduleVo);
                }
        );
        return source;
    }

    /**
     * 用户加入某一个活动
     * @return
     */
    @Override
    public R userAddAction(String uid , String aid) {
        // 参数校验
        SysUser sysUser = sysUserMapper.selectById(uid);
        AtomicBoolean flag = new AtomicBoolean(false);
        if(sysUser == null) return R.error();
        Arrays.asList(aid.split(",")).forEach(
                e -> {
                    WelActs welActs = welActsMapper.selectById(aid);
                    if(welActs != null){
                        // 判断用户之前是否加入了活动
                        flag.set(false);
                        String ids = welActs.getUids();
                        Arrays.stream(ids.split(",")).forEach(
                                item -> {
                                    if(item.equals(uid)) {
                                        flag.set(true);
                                    }
                                }
                        );
                        if(!flag.get()) {
                            // 用户加入活动
                            welActs.setUids(ids + "," + sysUser.getUid());
                            welActsMapper.updateById(welActs);
                            // 添加自增里
                            sysUser.setIncreaceNumber(sysUser.getIncreaceNumber() + welActs.getCbincrease());
                            sysUserMapper.updateById(sysUser);
                        }
                    }
                }
        );
        return R.success();
    }

    /**
     * 获取全部活动信息
     * @return
     */
    @Override
    public R getAllAction() {
        // 查询所有活动
        return null;
    }
}




