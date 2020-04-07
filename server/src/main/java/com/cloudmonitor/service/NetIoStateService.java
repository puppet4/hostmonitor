package com.cloudmonitor.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudmonitor.mapper.NetIoStateMapper;
import com.cloudmonitor.entity.NetIoState;
import com.github.pagehelper.PageHelper;
import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.UUIDUtil;

/**
 *
 * @ClassName:NetIoStateService.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: NetIoStateService.java
 *
 *
 */
@Service
public class NetIoStateService{

	public PageInfo selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<NetIoState> list = netIoStateMapper.selectByParams(params);
		PageInfo<NetIoState> pageInfo = new PageInfo<NetIoState>(list);
		return pageInfo;
	}

	public void save(NetIoState NetIoState) throws Exception {
		NetIoState.setId(UUIDUtil.getUUID());
		NetIoState.setCreateTime(DateUtil.getNowTime());
		NetIoState.setDateStr(DateUtil.getDateTimeString(NetIoState.getCreateTime()));
		netIoStateMapper.save(NetIoState);
	}
	
	public void saveRecord(List<NetIoState> recordList) throws Exception {
		 for(NetIoState as : recordList){
			 as.setId(UUIDUtil.getUUID());
			 as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
		 }
		 netIoStateMapper.insertList(recordList);
	}
	
	public int deleteById(String[] id) throws Exception {
		return netIoStateMapper.deleteById(id);
	}

	public NetIoState selectById(String id)  throws Exception{
		return netIoStateMapper.selectById(id);
	}

	public List<NetIoState> selectAllByParams(Map<String, Object> params)throws Exception {
		return netIoStateMapper.selectAllByParams(params);
	}
	
	
	@Autowired
	private NetIoStateMapper netIoStateMapper;



}
