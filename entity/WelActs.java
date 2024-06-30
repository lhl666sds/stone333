package com.lc.lcserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName wel_acts
 */
@TableName(value ="wel_acts")
@Data
public class WelActs implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer wid;

    /**
     * 公益名称
     */
    private String welname;

    /**
     * 公益简介
     */
    private String weldetail;

    /**
     * 公益内容
     */
    private String welmain;

    /**
     * 公益组织方
     */
    private String welfrom;

    /**
     * 公益受益方
     */
    private String welto;

    /**
     * 公益种类
     */
    private String type;

    /**
     * 筹集人数
     */
    private String number;

    /**
     * 用户id列表
     */
    private String uids;

    /**
     * 加入子增量
     */
    private Integer cbincrease;

    /**
     * 所属项目id
     */
    private Integer pid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}