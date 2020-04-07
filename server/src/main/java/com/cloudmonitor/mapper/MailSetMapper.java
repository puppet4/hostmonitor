package com.cloudmonitor.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cloudmonitor.entity.MailSet;

/**
 *
 * @ClassName:MailSetMapper.java
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: 查看磁盘IO使用情况
 *
 *
 */
@Repository
public interface MailSetMapper {

	
    public List<MailSet> selectAllByParams(Map<String, Object> map) throws Exception;
    
    public void save(MailSet MailSet) throws Exception;

    public int deleteById(String[] id) throws Exception;

    public int updateById(MailSet MailSet) throws Exception;
    

}
