package com.lc.lcserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 
 * @TableName banner_img
 */
@TableName(value ="banner_img")
@Data
public class BannerImg implements Serializable {
    /**
     * 轮播图id
     */
    @TableId(type = IdType.AUTO)
    private Integer bid;

    /**
     * 图片名
     */
    private String bname;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 图片类型
     */
    private Integer imgType;

    /**
     * 
     */
    private Date createTime;

    /**
     * 图片内容
     */
    private String bimg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}