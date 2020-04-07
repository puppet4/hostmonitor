package com.cloudmonitor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @ClassName:ApplicationContextHelper.java
 * @author: 刘威
 * @date: 2020年2月16日
 * @Description: ApplicationContextHelper.java
 *
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {    
    private static ApplicationContext applicationContext;    
    @Override    
    public void setApplicationContext( ApplicationContext applicationContext1 ) throws BeansException {    
    	applicationContext = applicationContext1;    
    }  
      
    public static ApplicationContext getApplicationContext(){  
        return applicationContext;  
    }  
      
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		return (T) applicationContext.getBean(clazz);
	}

}