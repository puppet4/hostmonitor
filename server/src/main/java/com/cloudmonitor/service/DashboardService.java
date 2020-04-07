package com.cloudmonitor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:DashboardService.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: 主面板信息管理
 *
 *
 */
@Service
public class DashboardService {
	

	/**
	 * 获取从今天开始，往前倒数天数的日期集合
	 * @param days
	 * @return
	 */
	public List<String>  getDateList(){
		int days = 7;
		List<String> dateList = new ArrayList<String>();
		String nowTime = DateUtil.getCurrentDateTime();
		String sevenDayBefore = DateUtil.getDateBefore(nowTime, days);
		for(int i = 0; i< days; i++){
			sevenDayBefore = DateUtil.getDateBefore(nowTime, i);
			dateList.add(sevenDayBefore.substring(0, 10));
		}
		return dateList;
	}

	/**
	 * 查看详细信息监控时候，组装日期查询条件
	 * @param params
	 * @param date
	 */
	public void setDateParam(String date,Map<String, Object> params){
		params.put(StaticKeys.SEARCH_START_TIME, date+" 00:00:00");
		params.put(StaticKeys.SEARCH_END_TIME, date+" 23:59:59");
	}
	
}
