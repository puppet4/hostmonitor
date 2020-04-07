package com.cloudmonitor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cloudmonitor.entity.DbTableCount;
import com.cloudmonitor.mapper.DbTableCountMapper;
import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName:DbTableCountCountService.java
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: DbTableCountService.java
 *
 *
 */
@Service
public class DbTableCountService {

	public PageInfo selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<DbTableCount> list = dbTableCountMapper.selectByParams(params);
		PageInfo<DbTableCount> pageInfo = new PageInfo<DbTableCount>(list);
		return pageInfo;
	}

	public void save(DbTableCount DbTableCount) throws Exception {
		DbTableCount.setId(UUIDUtil.getUUID());
		DbTableCount.setCreateTime(DateUtil.getNowTime());
		dbTableCountMapper.save(DbTableCount);
	}

	public void saveRecord(List<DbTableCount> recordList) throws Exception {
		if(recordList.size()<1){
			return;
		}
		for(DbTableCount as : recordList){
			as.setId(UUIDUtil.getUUID());
			as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
		}
		dbTableCountMapper.insertList(recordList);
	}


	public int countByParams(Map<String, Object> params) throws Exception{
		return dbTableCountMapper.countByParams(params);
	}

	@Transactional
	public int deleteById(String[] id) throws Exception {
		return dbTableCountMapper.deleteById(id);
	}
	
	public void updateById(DbTableCount DbTableCount)
			throws Exception {
		 dbTableCountMapper.updateById(DbTableCount);
	}

	public DbTableCount selectById(String id)  throws Exception{
		return dbTableCountMapper.selectById(id);
	}

	public List<DbTableCount> selectAllByParams(Map<String, Object> params)throws Exception {
		return dbTableCountMapper.selectAllByParams(params);
	}

	public int deleteByDate(Map<String, Object> map) throws Exception{
		return dbTableCountMapper.deleteByDate(map);
	}
	
	
	@Autowired
	private DbTableCountMapper dbTableCountMapper;



}
