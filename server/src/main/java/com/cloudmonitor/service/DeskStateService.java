package com.cloudmonitor.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudmonitor.mapper.DeskStateMapper;
import com.cloudmonitor.entity.DeskState;
import com.github.pagehelper.PageHelper;
import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.UUIDUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @ClassName:DeskStateService.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: DeskStateService.java
 *
 *
 */
@Service
public class DeskStateService{

	public PageInfo selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<DeskState> list = deskStateMapper.selectByParams(params);
		PageInfo<DeskState> pageInfo = new PageInfo<DeskState>(list);
		return pageInfo;
	}

	public void save(DeskState DeskState) throws Exception {
		DeskState.setId(UUIDUtil.getUUID());
		DeskState.setCreateTime(DateUtil.getNowTime());
		DeskState.setDateStr(DateUtil.getDateTimeString(DeskState.getCreateTime()));
		deskStateMapper.save(DeskState);
	}

	@Transactional
	public void saveRecord(List<DeskState> recordList) throws Exception {
		if(recordList.size()<1){
			return;
		}
		for(DeskState as : recordList){
			as.setId(UUIDUtil.getUUID());
			as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
		}
		deskStateMapper.insertList(recordList);
	}
	
	public int deleteById(String[] id) throws Exception {
		return deskStateMapper.deleteById(id);
	}

	public DeskState selectById(String id)  throws Exception{
		return deskStateMapper.selectById(id);
	}

	public List<DeskState> selectAllByParams(Map<String, Object> params)throws Exception {
		return deskStateMapper.selectAllByParams(params);
	}
	
	public int deleteByAccHname(Map<String,Object> params) throws Exception{
		return deskStateMapper.deleteByAccHname(params);
	}
	
	
	@Autowired
	private DeskStateMapper deskStateMapper;



}
