package com.cloudmonitor.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cloudmonitor.entity.AppInfo;

/**
 *
 * @ClassName:AppInfoDao.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: AppInfoDao.java
 *
 *
 */
@Repository
public interface AppInfoMapper {

    public List<AppInfo> selectAllByParams(Map<String, Object> map) throws Exception;
    
	public List<AppInfo> selectByParams(Map<String, Object> params) throws Exception;
	
    public AppInfo selectById(String id) throws Exception;
    
    public List<AppInfo> selectByAccountId(String accountId) throws Exception;
    
    public void save(AppInfo AppInfo) throws Exception;

    public void insertList(List<AppInfo> recordList) throws Exception;

    public void updateList(List<AppInfo> recordList) throws Exception;
    
    public int deleteById(String[] id) throws Exception;

    public int deleteByHostName(Map<String, Object> map) throws Exception;

    public int deleteByDate(Map<String, Object> map) throws Exception;

    public int countByParams(Map<String, Object> params) throws Exception;
    
    public int updateById(AppInfo AppInfo) throws Exception;
}
