package com.mwh.demo.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.mwh.demo.common.JsonUtils;
import com.mwh.demo.common.ResponseJSON;


public class CustomHandlerExceptionResolver extends SimpleMappingExceptionResolver{
	private static Logger log = Logger.getLogger(CustomHandlerExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		if(handlerMethod.getMethodAnnotation(ResponseBody.class)!=null){
			ModelAndView modelAndView = new ModelAndView();
			if(ex instanceof CustomValidateDomainException){
				Map<String, String> validateMap = ((CustomValidateDomainException)ex).getValidateMap();
				ResponseJSON responseJSON = ResponseJSON.instance();
				for (Map.Entry<String, String> validMessage : validateMap.entrySet()) {
					responseJSON.addValidationMessages(validMessage.getKey(), validMessage.getValue());
				}
				responseJSON.setStatus(false);
				modelAndView.addObject("exceptionInfo",JsonUtils.toJson(responseJSON));
				modelAndView.setView(new StringView("exceptionInfo"));
			}else if(ex instanceof CustomSystemException){
				ResponseJSON responseJSON = ResponseJSON.instance().addAlertMessage(ex.getMessage());
				responseJSON.setStatus(false);
				modelAndView.addObject("exceptionInfo",JsonUtils.toJson(responseJSON));
				modelAndView.setView(new StringView("exceptionInfo"));
			}else{
				log.error(ex);
				ResponseJSON responseJSON = ResponseJSON.instance().addAlertMessage(ex.getMessage());
				responseJSON.setStatus(false);
				modelAndView.addObject("exceptionInfo",JsonUtils.toJson(responseJSON));
				modelAndView.setView(new StringView("exceptionInfo"));
			}
			return modelAndView;
		}
		return super.resolveException(request, response, handler, ex);
	}

}
