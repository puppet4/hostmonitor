package com.cloudmonitor.util;

import cn.hutool.json.JSONObject;
import com.cloudmonitor.config.CommonConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @ClassName:TokenUtils.java
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: Md5加密处理
 *
 *
 */
@Component
public class TokenUtils {
	private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

	@Autowired
	private CommonConfig commonConfig;

	/**
	 * 验证agent的token和server的token是否一致
	 * @param agentJsonObject
	 * @return
	 */
	public boolean checkAgentToken(JSONObject agentJsonObject){
		if(null==agentJsonObject){
			return false;
		}
		String wgToken = MD5Utils.GetMD5Code(commonConfig.getWgToken());
		String agentWgToken =  agentJsonObject.getStr("wgToken");
		if(StringUtils.isEmpty(agentWgToken)){
			return false;
		}
		if(wgToken.equals(agentWgToken)){
			return true;
		}
		return false;

	}
    
}
