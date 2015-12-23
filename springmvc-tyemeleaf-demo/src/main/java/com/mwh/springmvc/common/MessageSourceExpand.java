package com.mwh.springmvc.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

public class MessageSourceExpand implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	private static MessageSource messageSource;

	private static LocaleResolver localeResolver;

	private String messageSourceName = "messageSource";

	private String localeResolverName = "localeResolver";

	public String getMessageSourceName() {
		return messageSourceName;
	}

	public void setMessageSourceName(String messageSourceName) {
		this.messageSourceName = messageSourceName;
	}

	public String getLocaleResolverName() {
		return localeResolverName;
	}

	public void setLocaleResolverName(String localeResolverName) {
		this.localeResolverName = localeResolverName;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		MessageSourceExpand.applicationContext = applicationContext;
		messageSource = (MessageSource)MessageSourceExpand.applicationContext.getBean(messageSourceName);
		localeResolver = (LocaleResolver)MessageSourceExpand.applicationContext.getBean(localeResolverName);
	}
	
	public static String getMessage(String key,String defaultValue,Object ... objs){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		return messageSource.getMessage(key, objs, defaultValue,localeResolver.resolveLocale(request));
	}
	
}
