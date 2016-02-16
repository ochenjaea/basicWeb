package com.skoh.sample.web.service.handler;

import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 서비스 핸들러
 * @author semoria
 *
 */
public interface ServiceHandler {
	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	public Object handle(Map<String, Object> param);
	
}
