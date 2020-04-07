package com.cloudmonitor.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.cloudmonitor.dto.ChartInfo;
import com.cloudmonitor.entity.*;
import com.cloudmonitor.service.*;
import com.cloudmonitor.util.FormatUtil;
import com.cloudmonitor.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudmonitor.util.DateUtil;
import com.cloudmonitor.util.staticvar.StaticKeys;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.nio.ch.Net;

/**
 *
 * @ClassName:DashboardCotroller.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: DashboardCotroller.java
 *
 *
 */
@Controller
@RequestMapping(value="/dash")
public class DashboardCotroller {
    
	private static final Logger logger = LoggerFactory.getLogger(DashboardCotroller.class);
	@Resource
	DashboardService dashboardService;
	@Resource
	CpuStateService cpuStateService;
	@Resource
	DeskStateService deskStateService;
	@Resource
	MemStateService memStateService;
	@Resource
	NetIoStateService netIoStateService;
	@Resource
	SysLoadStateService sysLoadStateService;
	@Resource
	DbTableService dbTableService;
	@Resource
	DbInfoService dbInfoService;
	@Resource
	TcpStateService tcpStateService;
	@Resource
	SystemInfoService systemInfoService;
	@Resource
    MailSetService mailSetService;
	@Resource
	AppInfoService appInfoService;
	@Resource
	private LogInfoService logInfoService;
	@Autowired
	HeathMonitorService heathMonitorService;
	@Autowired
	HostInfoService hostInfoService;
	
    /**
     * 根据条件查询host列表
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="main")
	public String mainList(Model model,HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<ChartInfo> chartInfoList = new ArrayList<ChartInfo>();
		try {
			int totalSystemInfoSize = systemInfoService.countByParams(params);
			model.addAttribute("totalSystemInfoSize", totalSystemInfoSize);
			int totalSizeApp = appInfoService.countByParams(params);
			model.addAttribute("totalSizeApp", totalSizeApp);

			params.put("memPer",90);
			int memPerSize_90 = systemInfoService.countByParams(params);
			double a = 0;
			if(totalSystemInfoSize!=0){
				a = (double)memPerSize_90/totalSystemInfoSize;
			}
			ChartInfo memPerSize_90_chart = new ChartInfo();
			memPerSize_90_chart.setItem("内存>90%");
			memPerSize_90_chart.setCount(memPerSize_90);
			memPerSize_90_chart.setPercent(FormatUtil.formatDouble(a,2));
			chartInfoList.add(memPerSize_90_chart);

			params.put("memPer",50);
			params.put("memPerLe",90);
			int memPerSize_50_90 = systemInfoService.countByParams(params);
			double b = 0;
			if(totalSystemInfoSize!=0){
				b = (double)memPerSize_50_90/totalSystemInfoSize;
			}
			ChartInfo memPerSize_50_90_chart = new ChartInfo();
			memPerSize_50_90_chart.setItem("内存>50%且<90%");
			memPerSize_50_90_chart.setCount(memPerSize_50_90);
			memPerSize_50_90_chart.setPercent(FormatUtil.formatDouble(b,2));
			chartInfoList.add(memPerSize_50_90_chart);
			params.clear();

			params.put("cpuPer",90);
			int cpuPerSize_90 = systemInfoService.countByParams(params);
			double c = 0;
			if(totalSystemInfoSize!=0){
				c = (double)cpuPerSize_90/totalSystemInfoSize;
			}
			ChartInfo cpuPerSize_90_chart = new ChartInfo();
			cpuPerSize_90_chart.setItem("CPU>90%");
			cpuPerSize_90_chart.setCount(cpuPerSize_90);
			cpuPerSize_90_chart.setPercent(FormatUtil.formatDouble(c,2));
			chartInfoList.add(cpuPerSize_90_chart);
			params.clear();

			params.put("cpuPer",90);
			params.put("memPer",90);
			int perSize_90_90 = systemInfoService.countByParams(params);
			double d = 0;
			if(totalSystemInfoSize!=0){
				d = (double)perSize_90_90/totalSystemInfoSize;
			}
			ChartInfo perSize_90_90_chart = new ChartInfo();
			perSize_90_90_chart.setItem("CPU和内存>90%");
			perSize_90_90_chart.setCount(perSize_90_90);
			perSize_90_90_chart.setPercent(FormatUtil.formatDouble(d,2));
			chartInfoList.add(perSize_90_90_chart);
			params.clear();

			params.put("memPerLe",50);
			params.put("cpuPerLe",50);
			int perSize_50_50 = systemInfoService.countByParams(params);
			double e = 0;
			if(totalSystemInfoSize!=0){
				e = (double)perSize_50_50/totalSystemInfoSize;
			}
			ChartInfo perSize_50_50_chart = new ChartInfo();
			perSize_50_50_chart.setItem("CPU和内存<50%");
			perSize_50_50_chart.setCount(perSize_50_50);
			perSize_50_50_chart.setPercent(FormatUtil.formatDouble(e,2));
			chartInfoList.add(perSize_50_50_chart);
			model.addAttribute("chartInfoList",  JSONUtil.parseArray(chartInfoList));
			params.clear();

			params.put("cpuPer",90);
			int memPerSizeApp = appInfoService.countByParams(params);
			model.addAttribute("memPerSizeApp", memPerSizeApp);
			params.clear();

			int logSize = logInfoService.countByParams(params);
			model.addAttribute("logSize",logSize);

			params.clear();
			int  dbTableSize = dbTableService.countByParams(params);
			model.addAttribute("dbTableSize",dbTableSize);

			Long  dbTableSum = dbTableService.sumByParams(params);
			model.addAttribute("dbTableSum",dbTableSum==null?0:dbTableSum);

			PageInfo pageInfoDbTableList = dbTableService.selectByParams(params,1,20);
			model.addAttribute("dbTableList",JSONUtil.parseArray(pageInfoDbTableList.getList()));

			int  dbInfoSize = dbInfoService.countByParams(params);
			model.addAttribute("dbInfoSize",dbInfoSize);

			int  heathSize = heathMonitorService.countByParams(params);
			model.addAttribute("heathSize",heathSize);
			params.put("heathStatus","200");
			int  heath200Size = heathMonitorService.countByParams(params);
			model.addAttribute("heath200Size",heath200Size);
			model.addAttribute("heatherrSize",(heathSize-heath200Size));



		} catch (Exception e) {
			logger.error("主面板信息异常：",e);
			logInfoService.save("dash/main","主面板信息错误："+e.toString(),StaticKeys.LOG_ERROR);
		}
		if(request.getParameter(StaticKeys.DASH_VIEW_ACCOUNT) != null){
			return "dashView/index";
		}else{
			return "index";
		}
	}

	/**
	 * 根据条件查询host列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="systemInfoList")
	public String systemInfoList(SystemInfo systemInfo,Model model,HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			PageInfo pageInfo = systemInfoService.selectByParams(params, systemInfo.getPage(), systemInfo.getPageSize());
			PageUtil.initPageNumber(pageInfo,model);
			model.addAttribute("pageUrl", "/dash/systemInfoList?1=1");
			model.addAttribute("page", pageInfo);
		} catch (Exception e) {
			logger.error("查询服务器列表错误：",e);
			logInfoService.save("查询服务器列表错误",e.toString(),StaticKeys.LOG_ERROR);
		}
		if(request.getParameter(StaticKeys.DASH_VIEW_ACCOUNT) != null){
			return "dashView/list";
		}else{
			return "host/list";
		}
	}



	/**
     * 根据IP查询服务器详情信息
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="detail")
	public String hostDetail(Model model,HttpServletRequest request) {
		//服务器名称
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)){
			return "error/500";
		}
		String hostname = "";
		try {
			SystemInfo systemInfo = systemInfoService.selectById(id);
			hostname = systemInfo.getHostname();
			model.addAttribute("systemInfo",systemInfo);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("hostname", systemInfo.getHostname());
			List<DeskState> deskStateList = deskStateService.selectAllByParams(params);
			model.addAttribute("deskStateList", deskStateList);
		} catch (Exception e) {
			logger.error("服务器详细信息错误：",e);
			logInfoService.save(hostname,"查看服务器详细信息错误",e.toString());
		}
		if(request.getParameter(StaticKeys.DASH_VIEW_ACCOUNT) != null){
			return "dashView/view";
		}else{
			return "host/view";
		}

	}

	/**
	 * 删除主机
	 * @param id
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="del")
	public String delete(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String errorMsg = "删除主机信息错误：";
		try {
			if(!StringUtils.isEmpty(request.getParameter("id"))){
				String[] ids = request.getParameter("id").split(",");
				for(String id : ids){
					SystemInfo sys = systemInfoService.selectById(id);
					if(!StringUtils.isEmpty(sys.getHostname())) {
						hostInfoService.deleteByIp(sys.getHostname().split(","));
					}
					logInfoService.save("删除主机："+sys.getHostname(),sys.getHostname(),StaticKeys.LOG_ERROR);
				}
				systemInfoService.deleteById(ids);
			}
		} catch (Exception e) {
			logger.error(errorMsg,e);
			logInfoService.save(errorMsg,e.toString(),StaticKeys.LOG_ERROR);
		}
		return "redirect:/dash/systemInfoList";
	}


	/**
	 * 根据IP查询服务器图形报表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="chart")
	public String hostChart(Model model,HttpServletRequest request) {
		//服务器名称
		String id = request.getParameter("id");
		String date = request.getParameter("date");
		if(StringUtils.isEmpty(id)){
			return "error/500";
		}
		String hostname = "";
		try {
			SystemInfo systemInfo = systemInfoService.selectById(id);
			hostname = systemInfo.getHostname();
			model.addAttribute("systemInfo",systemInfo);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("hostname", systemInfo.getHostname());
			if(StringUtils.isEmpty(date)){
				date = DateUtil.getCurrentDate();
			}
			dashboardService.setDateParam(date, params);
			model.addAttribute("datenow", date);
			model.addAttribute("dateList", dashboardService.getDateList());
			List<CpuState> cpuStateList = cpuStateService.selectAllByParams(params);
			model.addAttribute("cpuStateList", JSONUtil.parseArray(cpuStateList));
			model.addAttribute("cpuStateMaxVal",findCpuMaxVal(cpuStateList ));
			List<MemState> memStateList = memStateService.selectAllByParams(params);
			model.addAttribute("memStateList", JSONUtil.parseArray(memStateList));
			List<SysLoadState> ysLoadSstateList = sysLoadStateService.selectAllByParams(params);
			model.addAttribute("ysLoadSstateList",  JSONUtil.parseArray(ysLoadSstateList));
			model.addAttribute("ysLoadSstateMaxVal",findLoadMaxVal(ysLoadSstateList ));
			List<NetIoState> netIoStateList = netIoStateService.selectAllByParams(params);
			model.addAttribute("netIoStateList",  JSONUtil.parseArray(netIoStateList));
			model.addAttribute("netIoStateMaxVal",findnetIoStateMaxVal(netIoStateList));
		} catch (Exception e) {
			logger.error("服务器图形报表错误：",e);
			logInfoService.save(hostname,"图形报表错误",e.toString());
		}
		if(request.getParameter(StaticKeys.DASH_VIEW_ACCOUNT) != null){
			return "dashView/viewChart";
		}else{
			return "host/viewChart";
		}
	}
	
	

	private double findCpuMaxVal(List<CpuState> cpuStateList ){
		double maxval = 0;
		if(!CollectionUtil.isEmpty(cpuStateList)) {
			for (CpuState cpuState : cpuStateList) {
				if (null != cpuState.getIdle() && cpuState.getIdle() > maxval) {
					maxval = cpuState.getIdle();
				}
				if (null != cpuState.getSys() && cpuState.getSys() > maxval) {
					maxval = cpuState.getSys();
				}
				if (null != cpuState.getIowait() && cpuState.getIowait() > maxval) {
					maxval = cpuState.getIowait();
				}
			}
		}
		if(maxval==0){
			maxval=100;
		}
		return Math.ceil(maxval);
	}

	private double findLoadMaxVal(List<SysLoadState> ysLoadSstateList ){
		double maxval = 0;
		if(!CollectionUtil.isEmpty(ysLoadSstateList)) {
			for (SysLoadState sysLoadState : ysLoadSstateList) {
				if (null != sysLoadState.getOneLoad() && sysLoadState.getOneLoad() > maxval) {
					maxval = sysLoadState.getOneLoad();
				}
				if (null != sysLoadState.getFiveLoad() && sysLoadState.getFiveLoad() > maxval) {
					maxval = sysLoadState.getFiveLoad();
				}
				if (null != sysLoadState.getFifteenLoad() && sysLoadState.getFifteenLoad() > maxval) {
					maxval = sysLoadState.getFifteenLoad();
				}
			}
		}
		if(maxval==0){
			maxval=1;
		}
		return Math.ceil(maxval);
	}

	private double findnetIoStateMaxVal(List<NetIoState> netIoStateList ){
		double maxval = 0;
		if(!CollectionUtil.isEmpty(netIoStateList)) {
			for (NetIoState netIoState : netIoStateList) {
				if (null != netIoState.getRxpck() && new Double(netIoState.getRxpck()) > maxval) {
					maxval = new Double(netIoState.getRxpck());
				}
				if (null != netIoState.getTxpck() && new Double(netIoState.getTxpck()) > maxval) {
					maxval = new Double(netIoState.getTxpck());
				}
				if (null != netIoState.getRxbyt() && new Double(netIoState.getRxbyt()) > maxval) {
					maxval = new Double(netIoState.getRxbyt());
				}
				if (null != netIoState.getTxbyt() && new Double(netIoState.getTxbyt()) > maxval) {
					maxval = new Double(netIoState.getTxbyt());
				}
			}
		}
		if(maxval==0){
			maxval=1;
		}
		return Math.ceil(maxval);
	}
}