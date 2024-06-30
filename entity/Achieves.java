package com.lc.lcserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName achieves
 */
@TableName(value ="achieves")
@Data
public class Achieves implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer achid;

    /**
     * 成就名
     */
    private String achname;

    /**
     * 成就内容
     */
    private String achcont;

    /**
     * 种类
     */
    private String type;

    /**
     * 是否达成成就
     */
    private String isachieve;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Achieves other = (Achieves) that;
        return (this.getAchid() == null ? other.getAchid() == null : this.getAchid().equals(other.getAchid()))
            && (this.getAchname() == null ? other.getAchname() == null : this.getAchname().equals(other.getAchname()))
            && (this.getAchcont() == null ? other.getAchcont() == null : this.getAchcont().equals(other.getAchcont()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getIsachieve() == null ? other.getIsachieve() == null : this.getIsachieve().equals(other.getIsachieve()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAchid() == null) ? 0 : getAchid().hashCode());
        result = prime * result + ((getAchname() == null) ? 0 : getAchname().hashCode());
        result = prime * result + ((getAchcont() == null) ? 0 : getAchcont().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getIsachieve() == null) ? 0 : getIsachieve().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", achid=").append(achid);
        sb.append(", achname=").append(achname);
        sb.append(", achcont=").append(achcont);
        sb.append(", type=").append(type);
        sb.append(", isachieve=").append(isachieve);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}