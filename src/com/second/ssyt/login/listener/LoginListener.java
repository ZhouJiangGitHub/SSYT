package com.second.ssyt.login.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.second.ssyt.login.entity.UserEntity;

/**
 * Application Lifecycle Listener implementation class LoginListener
 *
 */
public class LoginListener implements HttpSessionAttributeListener {

	public Map<Integer, HttpSession> loginedInfoMap = new HashMap<>();
	
	
    public void attributeAdded(HttpSessionBindingEvent HttpSessionBindingEvent)  { 
        
    	String sessionAttributeName = HttpSessionBindingEvent.getName();
    	if("user".equals(sessionAttributeName)){
    		// 获取登录session存值（当前登录的帐号）
    		int sessionAttributeValue = ((UserEntity) HttpSessionBindingEvent.getValue()).getId();
			
			// 1、检查map里是否有此帐号，如果有此帐号，就把此帐号的session失效！
			HttpSession session = loginedInfoMap.get(sessionAttributeValue);
			if (session != null) {
				session.invalidate();
			}
			
			// 3、新帐号及关联的session放到map里
    		loginedInfoMap.put(sessionAttributeValue, HttpSessionBindingEvent.getSession());
    	}
    }


    public void attributeRemoved(HttpSessionBindingEvent HttpSessionBindingEvent)  { 
    	// 2、把之前的帐号在map缓存里移除
		// 获取登录session存值属性的名称
		String sessionAttributeName = HttpSessionBindingEvent.getName();
		if ("user".equals(sessionAttributeName)) {
			// 获取登录session存值（当前登录的帐号）
			int sessionAttributeValue = ((UserEntity) HttpSessionBindingEvent.getValue()).getId();
			// 移除登录信息
			loginedInfoMap.remove(sessionAttributeValue);
		}
    }

	
    public void attributeReplaced(HttpSessionBindingEvent HttpSessionBindingEvent)  { 
         
    }
	
}
