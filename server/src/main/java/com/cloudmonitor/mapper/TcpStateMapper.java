package com.cloudmonitor.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cloudmonitor.entity.TcpState;

/**
 *
 * @ClassName:TcpStateDao.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: 查看TCP连接状态
 *
 *
 */
@Repository
public interface TcpStateMapper {

	
    public List<TcpState> selectAllByParams(Map<String, Object> map) throws Exception;
    
    
	public List<TcpState> selectByParams(Map<String, Object> params) throws Exception;
	
	
    public TcpState selectById(String id) throws Exception;
    
    public void save(TcpState TcpState) throws Exception;
    
    
    public void insertList(List<TcpState> recordList) throws Exception;
    
    public int deleteByAccountAndDate(Map<String, Object> map) throws Exception;
   
    public int deleteById(String[] id) throws Exception;
    

}
