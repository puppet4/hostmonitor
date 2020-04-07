package com.cloudmonitor.entity;

import java.util.Date;

/**
 *
 * @ClassName:HeathMonitor.java
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: app端口信息
 *
 *
 */
public class HeathMonitor extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2913111613773445949L;



	/**
	 * 应用服务名称
	 */
	private String appName;

	/**
	 * 心跳检测Url
	 */
    private String heathUrl;
    
    /**
	 * 状态
	 */
    private String heathStatus;


	/**
     * 创建时间
     */
    private Date createTime;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getHeathUrl() {
		return heathUrl;
	}

	public void setHeathUrl(String heathUrl) {
		this.heathUrl = heathUrl;
	}

	public String getHeathStatus() {
		return heathStatus;
	}

	public void setHeathStatus(String heathStatus) {
		this.heathStatus = heathStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}