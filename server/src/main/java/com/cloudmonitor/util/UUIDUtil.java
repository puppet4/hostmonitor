package com.cloudmonitor.util;

import java.util.Random;
import java.util.UUID;


/**
 *
 * @ClassName:UUIDUtil.java     
 * @version v1.0
 * @author: 刘威
 * @date: 2020年1月16日
 * @Description: UUIDUtil.java
 *
 *
 */
public class UUIDUtil {
	
	public static String getUUID(){
		return String.valueOf(UUID.randomUUID()).replace("-", "");
	}
	
	/**
	 * 随机6位数字
	 * @return
	 */
	public static String getRandomSix(){
		return ""+new Random().nextInt(999999);
	}
	
}
