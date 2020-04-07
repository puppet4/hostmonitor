package com.cloudmonitor.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudmonitor.mapper.SystemInfoMapper;
import com.cloudmonitor.entity.SystemInfo;
import com.github.pagehelper.PageHelper;
import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.UUIDUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @ClassName:SystemInfoService.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: SystemInfoService.java
 *
 *
 */
@Service
public class SystemInfoService{

	public PageInfo selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<SystemInfo> list = systemInfoMapper.selectByParams(params);
		PageInfo<SystemInfo> pageInfo = new PageInfo<SystemInfo>(list);
		return pageInfo;
	}

	public void save(SystemInfo SystemInfo) throws Exception {
		SystemInfo.setId(UUIDUtil.getUUID());
		SystemInfo.setCreateTime(DateUtil.getNowTime());
		systemInfoMapper.save(SystemInfo);
	}

	@Transactional
	public void saveRecord(List<SystemInfo> recordList) throws Exception {
		if(recordList.size()<1){
			return;
		}
		for(SystemInfo as : recordList){
			as.setId(UUIDUtil.getUUID());
			as.setCreateTime(DateUtil.getNowTime());
		}
		systemInfoMapper.insertList(recordList);
	}

	@Transactional
	public void updateRecord(List<SystemInfo> recordList) throws Exception {
		if(recordList.size()<1){
			return;
		}
		systemInfoMapper.updateList(recordList);
	}

	@Transactional
	public void updateById(SystemInfo SystemInfo) throws Exception {
			systemInfoMapper.updateById(SystemInfo);
	}

	public int deleteById(String[] id) throws Exception {
		return systemInfoMapper.deleteById(id);
	}

	public SystemInfo selectById(String id)  throws Exception{
		return systemInfoMapper.selectById(id);
	}

	public List<SystemInfo> selectAllByParams(Map<String, Object> params)throws Exception {
		return systemInfoMapper.selectAllByParams(params);
	}

	public int countByParams(Map<String, Object> params) throws Exception{
		return systemInfoMapper.countByParams(params);
	}
	
	public List<SystemInfo> selectByAccountId(String accountId) throws Exception {
		return systemInfoMapper.selectByAccountId(accountId);
	}
	
	public int deleteByAccHname(Map<String,Object> params) throws Exception{
		return systemInfoMapper.deleteByAccHname(params);
	}
	
	
	@Autowired
	private SystemInfoMapper systemInfoMapper;



}
