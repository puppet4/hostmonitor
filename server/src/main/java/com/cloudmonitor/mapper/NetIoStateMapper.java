package com.cloudmonitor.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cloudmonitor.entity.NetIoState;

/**
 *
 * @ClassName:NetIoStateDao.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: 查看网络设备的吞吐率
 *
 *
 */
@Repository
public interface NetIoStateMapper {

	
    public List<NetIoState> selectAllByParams(Map<String, Object> map) throws Exception;
    
    
	public List<NetIoState> selectByParams(Map<String, Object> params) ;
	
	
    public NetIoState selectById(String id) throws Exception;
    
    
    public void save(NetIoState NetIoState) throws Exception;
    
    
    public void insertList(List<NetIoState> recordList) throws Exception;
    
    public int deleteByAccountAndDate(Map<String, Object> map) throws Exception;
    
    public int deleteById(String[] id) throws Exception;
    

}
