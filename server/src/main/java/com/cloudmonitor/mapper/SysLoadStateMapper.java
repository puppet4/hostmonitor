package com.cloudmonitor.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cloudmonitor.entity.SysLoadState;

/**
 *
 * @ClassName:SysLoadStateDao.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: 查看uptime查看系统负载状态
 *
 *
 */
@Repository
public interface SysLoadStateMapper {

	
    public List<SysLoadState> selectAllByParams(Map<String, Object> map) throws Exception;
    
    
	public List<SysLoadState> selectByParams(Map<String, Object> params) throws Exception;
	
	
    public SysLoadState selectById(String id) throws Exception;
    
    public void save(SysLoadState SysLoadState) throws Exception;
    
    
    public void insertList(List<SysLoadState> recordList) throws Exception;
    
    public int deleteByAccountAndDate(Map<String, Object> map) throws Exception;
   
    public int deleteById(String[] id) throws Exception;
    

}
