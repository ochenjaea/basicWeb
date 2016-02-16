package com.skoh.sample.web.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceContext {
	
	public ServiceContext() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ServiceContext(HttpServletRequest request, HttpServletResponse response, String serviceName) {
		// TODO Auto-generated constructor stub
		
		this.request = request;
		this.response = response;
		this.serviceName = serviceName;
	}
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private String serviceName;
	
	private List<Map<String, Object>> paramList;
	
	private Map<String, Object> paramMap;
	
	private Object paramObj;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public List<Map<String, Object>> getParamList() {
		return paramList;
	}
	
	public void setParamList(List<Map<String, Object>> paramList) {
		this.paramList = paramList;
	}
	
	public void addParamList(Map<String, Object> param) {
		this.paramList.add(param);
	}
	
	public Map<String, Object> getParamMap() {
		return paramMap;
	}
	
	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	public void putParamMap(String key, Object obj) {
		this.paramMap.put(key, obj);
	}
	
	public Object getParamObj() {
		return paramObj;
	}
	
	public void setParamObj(Object paramObj) {
		this.paramObj = paramObj;
	}
	
	public Object getParamObj(String key) {
		Object paramObj = null;
		Map<String, Object> paramMap = getParamMap();
		paramObj = paramMap.get(key);
		return paramObj;
	}
}
