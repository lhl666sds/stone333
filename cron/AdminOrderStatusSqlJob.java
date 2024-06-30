package com.lc.lcserve.cron;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lc.lcserve.entity.SysUser;
import com.lc.lcserve.entity.TransOrder;
import com.lc.lcserve.mapper.SysUserMapper;
import com.lc.lcserve.mapper.TransOrderMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.util.List;


public class AdminOrderStatusSqlJob implements Job {


    @Resource
    private TransOrderMapper transOrderMapper;

    /**
     * 执行 的定时任务：
     *      需要去数据库中查询祖主页的信息  并且更新静态信息类
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 批量查询订单数据库，如果存在订单状态为未支付的则转为已支付
        LambdaQueryWrapper<TransOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TransOrder::getOstate , "1");
        List<TransOrder> transOrders = transOrderMapper.selectList(queryWrapper);
        transOrders.forEach(
                e -> {
                    e.setOstate("2");
                    transOrderMapper.updateById(e);
                }
        );
    }

}
