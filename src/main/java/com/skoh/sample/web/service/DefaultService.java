package com.skoh.sample.web.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.skoh.sample.common.constant.ServiceConstant;

/**
 * 기본 서비스 기능 모음
 * @author semoria
 *
 */
@Service
public class DefaultService extends AbstractService {
	private static Logger logger = Logger.getLogger(DefaultService.class);
	
	public DefaultService() {
		logger.debug("Create DefaultService");
	}
	
	/**
	 * Update
	 * @param context
	 */
	public Object update(Map<String, Object> param) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", this.commonDao.update(param.get(ServiceConstant.SERVICE_QUERY_ID).toString(), param));
		return returnMap;
	}
	/**
	 * Insert
	 * @param context
	 */
	public Object insert(Map<String, Object> param) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", this.commonDao.insert(param.get(ServiceConstant.SERVICE_QUERY_ID).toString(), param));
		return returnMap;
	}
	/**
	 * Delete
	 * @param context
	 */
	public Object delete(Map<String, Object> param) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", this.commonDao.delete(param.get(ServiceConstant.SERVICE_QUERY_ID).toString(), param));
		return returnMap;
	}
}
