package com.lc.lcserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

/**
 *  具体商品表
 * @TableName trans_goods
 */
@TableName(value ="trans_goods")
@Data
public class TransGoods implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer gid;

    /**
     * 商品名
     */
    private String gname;

    /**
     * 商品价格
     */
    private String gprice;

    /**
     * 
     */
    private Integer soldNum;

    /**
     * 商品数量
     */
    private Integer gamount;

    /**
     * 商品简介
     */
    private String gdetail;

    /**
     * 商品种类
     */
    private String gtype;

    /**
     * 
     */
    private String gimg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}