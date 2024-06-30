package com.lc.lcserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * @TableName atlas
 */
@TableName(value ="atlas")
@Data
public class Atlas implements Serializable {
    /**
     * 图鉴id
     */
    @TableId(type = IdType.AUTO)
    private Integer aid;

    /**
     * 图鉴名
     */
    private String atlaTital;

    /**
     * 图鉴详情
     */
    private String atlaCont;

    /**
     * 图鉴分类
     */
    private String type;

    /**
     * 图鉴图
     */
    private String atlaImg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}