package com.cloudmonitor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudmonitor.mapper.IntrusionInfoMapper;
import com.cloudmonitor.entity.IntrusionInfo;
import com.github.pagehelper.PageHelper;
import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.UUIDUtil;

/**
 *
 * @ClassName:IntrusionInfoService.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: IntrusionInfoService.java
 *
 *
 */
@Service
public class IntrusionInfoService{

	public PageInfo selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<IntrusionInfo> list = intrusionInfoMapper.selectByParams(params);
		PageInfo<IntrusionInfo> pageInfo = new PageInfo<IntrusionInfo>(list);
		return pageInfo;
	}

	public void save(IntrusionInfo IntrusionInfo) throws Exception {
		IntrusionInfo.setId(UUIDUtil.getUUID());
		IntrusionInfo.setCreateTime(DateUtil.getNowTime());
		intrusionInfoMapper.save(IntrusionInfo);
	}
	
	public void saveRecord(List<IntrusionInfo> recordList) throws Exception {
		 Map<String,Object> map= new HashMap<String,Object>();
		 for(IntrusionInfo as : recordList){
			 as.setId(UUIDUtil.getUUID());
			 map.put("hostname", as.getHostname());
			 intrusionInfoMapper.deleteByAccHname(map);
		 }
		 intrusionInfoMapper.insertList(recordList);
	}
	
	public int deleteById(String[] id) throws Exception {
		return intrusionInfoMapper.deleteById(id);
	}

	public IntrusionInfo selectById(String id)  throws Exception{
		return intrusionInfoMapper.selectById(id);
	}

	public List<IntrusionInfo> selectAllByParams(Map<String, Object> params)throws Exception {
		return intrusionInfoMapper.selectAllByParams(params);
	}
	
	public List<IntrusionInfo> selectByAccountId(String accountId) throws Exception {
		return intrusionInfoMapper.selectByAccountId(accountId);
	}
	

	@Autowired
	private IntrusionInfoMapper intrusionInfoMapper;



}
