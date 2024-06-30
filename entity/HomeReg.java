package com.lc.lcserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName home_reg
 */
@TableName(value ="home_reg")
@Data
public class HomeReg implements Serializable {
    /**
     * 章程id
     */
    @TableId(type = IdType.AUTO)
    private Integer reid;

    /**
     * 章程标题
     */
    private String retital;

    /**
     * 章程内容
     */
    private String recont;

    /**
     * 图片
     */
    private String rimg;

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
        HomeReg other = (HomeReg) that;
        return (this.getReid() == null ? other.getReid() == null : this.getReid().equals(other.getReid()))
            && (this.getRetital() == null ? other.getRetital() == null : this.getRetital().equals(other.getRetital()))
            && (this.getRecont() == null ? other.getRecont() == null : this.getRecont().equals(other.getRecont()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReid() == null) ? 0 : getReid().hashCode());
        result = prime * result + ((getRetital() == null) ? 0 : getRetital().hashCode());
        result = prime * result + ((getRecont() == null) ? 0 : getRecont().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reid=").append(reid);
        sb.append(", retital=").append(retital);
        sb.append(", recont=").append(recont);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}