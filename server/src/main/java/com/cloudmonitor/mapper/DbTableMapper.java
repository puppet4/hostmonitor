package com.cloudmonitor.mapper;

import com.cloudmonitor.entity.DbTable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName:DbTableMapper.java
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: DbTableDao.java
 *
 *
 */
@Repository
public interface DbTableMapper {

    public List<DbTable> selectAllByParams(Map<String, Object> map) throws Exception;
    
	public List<DbTable> selectByParams(Map<String, Object> params) throws Exception;
	
    public DbTable selectById(String id) throws Exception;
    
    public void save(DbTable DbTable) throws Exception;

    public int deleteById(String[] id) throws Exception;

    public int deleteByDbInfoId(String dbInfoId) throws Exception;

    public void updateList(List<DbTable> recordList) throws Exception;

    public int countByParams(Map<String, Object> params) throws Exception;

    public Long sumByParams(Map<String, Object> params) throws Exception;
    
    public void updateById(DbTable DbTable) throws Exception;
}
