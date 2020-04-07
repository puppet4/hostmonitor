package com.cloudmonitor.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudmonitor.mapper.SysLoadStateMapper;
import com.cloudmonitor.entity.SysLoadState;
import com.github.pagehelper.PageHelper;
import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.UUIDUtil;

/**
 *
 * @ClassName:SysLoadStateService.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: SysLoadStateService.java
 *
 *
 */
@Service
public class SysLoadStateService{

	public PageInfo selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<SysLoadState> list = sysLoadStateMapper.selectByParams(params);
		PageInfo<SysLoadState> pageInfo = new PageInfo<SysLoadState>(list);
		return pageInfo;
	}

	public void save(SysLoadState SysLoadState) throws Exception {
		SysLoadState.setId(UUIDUtil.getUUID());
		SysLoadState.setCreateTime(DateUtil.getNowTime());
		SysLoadState.setDateStr(DateUtil.getDateTimeString(SysLoadState.getCreateTime()));
		sysLoadStateMapper.save(SysLoadState);
	}
	
	public void saveRecord(List<SysLoadState> recordList) throws Exception {
		if(recordList.size()<1){
			return;
		}
		 for(SysLoadState as : recordList){
			 as.setId(UUIDUtil.getUUID());
			 as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
		 }
		 sysLoadStateMapper.insertList(recordList);
	}
	
	public int deleteById(String[] id) throws Exception {
		return sysLoadStateMapper.deleteById(id);
	}

	public SysLoadState selectById(String id)  throws Exception{
		return sysLoadStateMapper.selectById(id);
	}

	public List<SysLoadState> selectAllByParams(Map<String, Object> params)throws Exception {
		return sysLoadStateMapper.selectAllByParams(params);
	}
	
	
	@Autowired
	private SysLoadStateMapper sysLoadStateMapper;



}
