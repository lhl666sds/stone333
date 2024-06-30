package com.lc.lcserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * @TableName home_info
 */
@TableName(value ="home_info")
@Data
public class HomeInfo implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer infoid;

    /**
     * 
     */
    private String infoname;

    /**
     * 
     */
    private String infoimg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}