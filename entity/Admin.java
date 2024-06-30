package com.lc.lcserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 */
@TableName(value ="admin")
@Data
public class Admin implements Serializable {
    /**
     * 
     */
    @TableId
    private String admin;

    /**
     * 
     */
    private String password;

    private Integer uid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}