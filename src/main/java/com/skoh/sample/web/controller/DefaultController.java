package com.skoh.sample.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skoh.sample.common.dao.CommonDao;
import com.skoh.sample.common.util.WebUtil;
import com.skoh.sample.web.model.ServiceContext;
import com.skoh.sample.web.service.handler.ServiceHandler;


/**
 * 기본 Controller
 *
 */
@Controller
public class DefaultController extends AbstractController {

	private static Logger logger = Logger.getLogger(DefaultController.class);
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired AsyncTest asyncTest;
	
	/**
	 * Constructor
	 */
	public DefaultController() {
		logger.debug("Create DefaultController");
	}
	/**
	 * 페이지 전환용
	 * @param request
	 * @param response
	 * @param param
	 * @return
	 * @throws IOException 
	 */
		
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/pageView/**",method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView pageView(HttpSession session, HttpServletRequest request, ModelMap modelMap) {
		String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		ModelAndView mav = WebUtil.getModelAndView(restOfTheUrl);
	
		Enumeration<String> names = request.getParameterNames();
		
		String name = "";
		// 이전 페이지에서 던진 파라미터 넣기
		while(names.hasMoreElements()) {
			name = names.nextElement();
			mav.addObject(name, request.getParameter(name));
		}
			
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/pageView/sampleListPage",method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView sampleListPage(HttpSession session, HttpServletRequest request, ModelMap modelMap) {
		String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		ModelAndView mav = WebUtil.getModelAndView(restOfTheUrl);
	
		Enumeration<String> names = request.getParameterNames();
		
		String name = "";
		// 이전 페이지에서 던진 파라미터 넣기
		while(names.hasMoreElements()) {
			name = names.nextElement();
			mav.addObject(name, request.getParameter(name));
		}
		
		List<Object> testList = new ArrayList<Object>();
		
		for(int i=0;i<10;i++){
			testList.add(i);
		}
		//스프링 비동기 함수 호출
		try {
			asyncTest.asyncTest();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			error();
		}
		mav.addObject("list",testList);
		
		return mav;
		
	}
	
	public String error(){
		
		return "";
	}
	
	public void sampleDBQuery(){
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> returnMap = new ArrayList<Map<String,Object>>();
		try{
			returnMap = this.commonDao.selectToListMap("com.skoh.sample.common.selectGetUser", paramMap);
			
		}catch(Exception e){
			//e.printStackTrace();
			error();
		}
		
		logger.debug(returnMap);
	}
	
	
	/**
	 * 일반적인 Ajax 호출 
	 * @param request
	 * @param params
	 * @param serviceName
	 * @return
	 */
	
	@RequestMapping(value="/ajax/"+"{serviceName}",method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object webRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceName, HttpSession session) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("hi", "hello");
	    
		return resultMap;
	}
	
	/**
	 * 유저 서비스 컨트롤러
	 * @param request
	 * @param params
	 * @param serviceName
	 * @return
	 */
	@RequestMapping(value="/common/"+"{serviceName}",method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Object getCommonList(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceName, @RequestBody Map<String, Object> paramMap) throws Exception {
		ServiceContext serviceContext = new ServiceContext(request, response, serviceName);
		serviceContext.setParamMap(paramMap);
		Object serviceObject = null;
		
		//코드 정보 조회
		paramMap.put("USER_ID", "test");
		
		serviceObject = this.commonDao.selectToObj("com.skoh.sample.common.loginUser", paramMap);
		
		return serviceObject;
	}

	
}
