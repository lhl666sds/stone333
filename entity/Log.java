package com.lc.lcserve.entity;

public class Log {

    // id
    private Long id;

    // 操作用户
    private String requestUser;

    // 接口类型
    private Integer interfaceType;

    // 请求携带信息
    private String requestParams;

    // 请求地址
    private String requestUrl;

    // 响应参数
    private String responseInfo;

    // 请求时间
    private Long happenTime;

    // 请求耗时
    private Long elapsedTime;

    // 请求成功与否
    private Integer status;

    // 详细日志
    private String logDetails;

    // 描述
    private String description;

    @Override
    public String toString() {
        return "RsglLog{" +
                "id=" + id +
                ", requestUser='" + requestUser + '\'' +
                ", interfaceType=" + interfaceType +
                ", requestParams='" + requestParams + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", responseInfo='" + responseInfo + '\'' +
                ", happenTime=" + happenTime +
                ", elapsedTime=" + elapsedTime +
                ", status=" + status +
                ", logDetails='" + logDetails + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public Integer getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(Integer interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }

    public Long getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(Long happenTime) {
        this.happenTime = happenTime;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
