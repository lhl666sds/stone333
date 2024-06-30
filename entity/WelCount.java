package com.lc.lcserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName wel_count
 */
@TableName(value ="wel_count")
@Data
public class WelCount implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer weid;

    /**
     * 
     */
    private String wname;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String organization;

    /**
     * 
     */
    private String others;

    /**
     * 
     */
    private Date createTime;

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
        WelCount other = (WelCount) that;
        return (this.getWeid() == null ? other.getWeid() == null : this.getWeid().equals(other.getWeid()))
            && (this.getWname() == null ? other.getWname() == null : this.getWname().equals(other.getWname()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getOrganization() == null ? other.getOrganization() == null : this.getOrganization().equals(other.getOrganization()))
            && (this.getOthers() == null ? other.getOthers() == null : this.getOthers().equals(other.getOthers()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWeid() == null) ? 0 : getWeid().hashCode());
        result = prime * result + ((getWname() == null) ? 0 : getWname().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getOrganization() == null) ? 0 : getOrganization().hashCode());
        result = prime * result + ((getOthers() == null) ? 0 : getOthers().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", weid=").append(weid);
        sb.append(", wname=").append(wname);
        sb.append(", phone=").append(phone);
        sb.append(", organization=").append(organization);
        sb.append(", others=").append(others);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}