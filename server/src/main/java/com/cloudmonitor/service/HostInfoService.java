package com.cloudmonitor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cloudmonitor.entity.HostInfo;
import com.cloudmonitor.mapper.HostInfoMapper;
import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName:HostInfoService.java
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: HostInfoService.java
 *
 *
 */
@Service
public class HostInfoService {

	public PageInfo selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<HostInfo> list = hostInfoMapper.selectByParams(params);
		PageInfo<HostInfo> pageInfo = new PageInfo<HostInfo>(list);
		return pageInfo;
	}

	public void save(HostInfo HostInfo) throws Exception {
		HostInfo.setId(UUIDUtil.getUUID());
		HostInfo.setCreateTime(DateUtil.getNowTime());
		hostInfoMapper.save(HostInfo);
	}
	
	@Transactional
	public int deleteById(String[] id) throws Exception {
		return hostInfoMapper.deleteById(id);
	}

	@Transactional
	public int deleteByIp(String[] ip) throws Exception {
		return hostInfoMapper.deleteByIp(ip);
	}
	
	public void updateById(HostInfo HostInfo)
			throws Exception {
		 hostInfoMapper.updateById(HostInfo);
	}

	public HostInfo selectById(String id)  throws Exception{
		return hostInfoMapper.selectById(id);
	}

	public List<HostInfo> selectAllByParams(Map<String, Object> params)throws Exception {
		return hostInfoMapper.selectAllByParams(params);
	}
	
	
	@Autowired
	private HostInfoMapper hostInfoMapper;



}
