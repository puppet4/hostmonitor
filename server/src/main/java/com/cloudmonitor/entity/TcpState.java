package com.cloudmonitor.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @ClassName:TcpState.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: 查看TCP连接状态
 *
 *
 */
public class TcpState extends BaseEntity{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -299667815095138020L;
	/**
	 * host名称
	 */
	private String hostname;

	/**
	 * 每秒本地发起的TCP连接数，既通过connect调用创建的TCP连接；,active/s
	 */
    private String active;

    /**
	 * 每秒远程发起的TCP连接数，即通过accept调用创建的TCP连接,passive/s
	 */
    private String passive;
    
    /**
	 * 每秒TCP重传数量,retrans/s
	 */
    private String retrans;
    
    /**
     * 添加时间
     * yyyy-MM-dd hh:mm:ss
     */
    private String dateStr;
    
    /**
     * 创建时间
     */
    private Date createTime; 


	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getPassive() {
		return passive;
	}

	public void setPassive(String passive) {
		this.passive = passive;
	}

	public String getRetrans() {
		return retrans;
	}

	public void setRetrans(String retrans) {
		this.retrans = retrans;
	}

	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDateStr() {
		if(!StringUtils.isEmpty(dateStr)&&dateStr.length()>16){
			return dateStr.substring(5);
		}
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}


	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	
    
   
}