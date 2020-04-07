package com.cloudmonitor.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cloudmonitor.entity.LogInfo;

/**
 *
 * @ClassName:LogInfoDao.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: 查看日志信息
 *
 *
 */
@Repository
public interface LogInfoMapper {

	
    public List<LogInfo> selectAllByParams(Map<String, Object> map);

    public int countByParams(Map<String, Object> params) throws Exception;

	public List<LogInfo> selectByParams(Map<String, Object> params) throws Exception;
	
    public LogInfo selectById(String id) throws Exception;
    
    public void save(LogInfo LogInfo) throws Exception;
    
    public int deleteById(String[] id) throws Exception;

    public void insertList(List<LogInfo> recordList) throws Exception;

    public int deleteByDate(Map<String, Object> map) throws Exception;
   
}
