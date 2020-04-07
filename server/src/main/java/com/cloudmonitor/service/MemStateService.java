package com.cloudmonitor.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudmonitor.mapper.MemStateMapper;
import com.cloudmonitor.entity.MemState;
import com.github.pagehelper.PageHelper;
import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.UUIDUtil;

/**
 *
 * @ClassName:MemStateService.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: MemStateService.java
 *
 *
 */
@Service
public class MemStateService{

	public PageInfo selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<MemState> list = memStateMapper.selectByParams(params);
		PageInfo<MemState> pageInfo = new PageInfo<MemState>(list);
		return pageInfo;
	}

	public void save(MemState MemState) throws Exception {
		MemState.setId(UUIDUtil.getUUID());
		MemState.setCreateTime(DateUtil.getNowTime());
		MemState.setDateStr(DateUtil.getDateTimeString(MemState.getCreateTime()));
		memStateMapper.save(MemState);
	}
	
	public void saveRecord(List<MemState> recordList) throws Exception {
		if(recordList.size()<1){
			return;
		}
		 for(MemState as : recordList){
			 as.setId(UUIDUtil.getUUID());
			 as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
		 }
		 memStateMapper.insertList(recordList);
	}
	
	public int deleteById(String[] id) throws Exception {
		return memStateMapper.deleteById(id);
	}

	public MemState selectById(String id)  throws Exception{
		return memStateMapper.selectById(id);
	}

	public List<MemState> selectAllByParams(Map<String, Object> params)throws Exception {
		return memStateMapper.selectAllByParams(params);
	}
	
	
	@Autowired
	private MemStateMapper memStateMapper;



}
