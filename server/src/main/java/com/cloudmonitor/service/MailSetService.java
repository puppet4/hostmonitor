package com.cloudmonitor.service;

import com.cloudmonitor.entity.MailSet;
import com.cloudmonitor.mapper.MailSetMapper;
import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName:DiskIoStateService.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: DiskIoStateService.java
 *
 *
 */
@Service
public class MailSetService {


	public void save(MailSet MailSet) throws Exception {
		MailSet.setId(UUIDUtil.getUUID());
		MailSet.setCreateTime(DateUtil.getNowTime());
		MailSet.setFromMailName(MailSet.getFromMailName().trim());
		MailSet.setFromPwd(MailSet.getFromPwd().trim());
		MailSet.setToMail(MailSet.getToMail().trim());
		MailSet.setSmtpHost(MailSet.getSmtpHost().trim());
		mailSetMapper.save(MailSet);
	}
	

	public int deleteById(String[] id) throws Exception {
		return mailSetMapper.deleteById(id);
	}

	public List<MailSet> selectAllByParams(Map<String, Object> params)throws Exception {
		return mailSetMapper.selectAllByParams(params);
	}

	public int updateById(MailSet MailSet) throws Exception{
		return mailSetMapper.updateById(MailSet);
	}
	
	
	@Autowired
	private MailSetMapper mailSetMapper;



}
