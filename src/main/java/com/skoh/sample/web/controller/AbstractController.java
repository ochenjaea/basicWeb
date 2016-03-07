package com.skoh.sample.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.skoh.sample.common.converter.ParametersConverter;
import com.skoh.sample.web.model.ServiceContext;

/**
 * 추상화 컨트롤러
 * @author semoria
 *
 */
@EnableWebMvc
public abstract class AbstractController {	

	/**
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected ServiceContext preExecute(HttpServletRequest request) {
		 ServiceContext serviceContext = new ServiceContext();
		 serviceContext.setRequest(request);
	        
		 serviceContext.setParamMap(ParametersConverter.convertObject(request.getParameterMap()));
	        
		 return serviceContext;
	}
		
	/**
	 * 
	 * @param request
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected ServiceContext preExecute(HttpServletRequest request, Map<String, Object> param) {
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setRequest(request);
        
        serviceContext.setParamMap(ParametersConverter.convertObject(param));
        
        return serviceContext;
    }
	
	/**
	 * 
	 * @param request
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected ServiceContext preExecute(HttpServletRequest request, List<Map<String, Object>> params) {
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setRequest(request);
        
        for(Map<String, Object> map : params) {
        	serviceContext.setParamMap(ParametersConverter.convertObject(map));
        }
        
        return serviceContext;
    }
}
