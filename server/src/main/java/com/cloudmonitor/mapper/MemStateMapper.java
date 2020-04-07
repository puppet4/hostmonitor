package com.cloudmonitor.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cloudmonitor.entity.MemState;

/**
 *
 * @ClassName:MemStateDao.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: 查看内存使用情况
 *
 *
 */
@Repository
public interface MemStateMapper {

	
    public List<MemState> selectAllByParams(Map<String, Object> map) throws Exception ;
    
	public List<MemState> selectByParams(Map<String, Object> params) throws Exception;
	
    public MemState selectById(String id) throws Exception;
    
    public void save(MemState MemState) throws Exception;
    
    public void insertList(List<MemState> recordList) throws Exception;
    
    public int deleteByAccountAndDate(Map<String, Object> map) throws Exception;

    public int deleteById(String[] id) throws Exception;
    

}
