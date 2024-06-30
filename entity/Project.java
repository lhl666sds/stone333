package com.lc.lcserve.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author
 * @date 2023/4/22&22:41
 **/
@Data
public class Project {


    // 项目id
    @TableId
    private Integer pid;

    // 活动列表
    private String aids;

    // 项目名称
    private String name;

    // 项目图片路径
    private String pimg;

    // 项目描述
    private String pdescript;

}
