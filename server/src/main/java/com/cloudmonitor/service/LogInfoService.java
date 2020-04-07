package com.cloudmonitor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudmonitor.mapper.LogInfoMapper;
import com.cloudmonitor.entity.LogInfo;
import com.github.pagehelper.PageHelper;
import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.UUIDUtil;

/**
 *
 * @ClassName:LogInfoService.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: LogInfoService.java
 *
 *
 */
@Service
public class LogInfoService{

	private static final Logger logger = LoggerFactory.getLogger(LogInfoService.class);
	
	public PageInfo selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<LogInfo> list = logInfoMapper.selectByParams(params);
		PageInfo<LogInfo> pageInfo = new PageInfo<LogInfo>(list);
		return pageInfo;
	}

	public void saveRecord(List<LogInfo> recordList) throws Exception {
		if(recordList.size()<1){
			return;
		}
		Map<String,Object> map= new HashMap<String,Object>();
		for(LogInfo as : recordList){
			as.setId(UUIDUtil.getUUID());
		}
		logInfoMapper.insertList(recordList);
	}

	public void save(String hostname,String infoContent,String state){
		LogInfo logInfo = new LogInfo();
		logInfo.setHostname(hostname);
		logInfo.setInfoContent(infoContent);
		logInfo.setState(state);
		logInfo.setId(UUIDUtil.getUUID());
		logInfo.setCreateTime(DateUtil.getNowTime());
		try {
			 logInfoMapper.save(logInfo);
		} catch (Exception e) {
			logger.error("保存日志信息异常：",e);
		}
	}

	public int countByParams(Map<String, Object> params) throws Exception{
		return logInfoMapper.countByParams(params);
	}
	
	public int deleteById(String[] id) throws Exception {
		return logInfoMapper.deleteById(id);
	}

	public LogInfo selectById(String id)  throws Exception{
		return logInfoMapper.selectById(id);
	}

	public List<LogInfo> selectAllByParams(Map<String, Object> params)throws Exception {
		return logInfoMapper.selectAllByParams(params);
	}
	
	@Autowired
	private LogInfoMapper logInfoMapper;



}
