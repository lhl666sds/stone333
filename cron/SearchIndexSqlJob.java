package com.lc.lcserve.cron;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lc.lcserve.entity.SysUser;
import com.lc.lcserve.mapper.SysUserMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.util.List;


public class SearchIndexSqlJob implements Job {


    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 执行 的定时任务：
     *      需要去数据库中查询祖主页的信息  并且更新静态信息类
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 批量查询用表
        List<SysUser> userInfos = sysUserMapper.selectList(null);
        userInfos.forEach(
                e -> {
                    int i = Integer.parseInt(e.getBalance()) + e.getIncreaceNumber();
                    e.setBalance(Integer.toString(i));
                    sysUserMapper.updateById(e);
                }
        );

    }

}
