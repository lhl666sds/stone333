package com.lc.lcserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName trans_order
 */
@TableName(value ="trans_order")
@Data
public class TransOrder implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer oid;

    /**
     * 
     */
    private Integer gid;

    /**
     * 商品名
     */
    private Integer uid;

    /**
     * 商品数量
     */
    private String onumber;

    /**
     * 商品价格
     */
    private Integer oprice;

    /**
     * 订单时间
     */
    private Date ordtime;

    /**
     * 订单状态   待付款、待发货、已发货 ， 已结束  对应 0 , 1 , 2 , 3
     */
    private String ostate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}