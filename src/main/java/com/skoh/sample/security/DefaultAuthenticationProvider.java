package com.skoh.sample.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.skoh.sample.common.dao.CommonDao;

public class DefaultAuthenticationProvider implements AuthenticationProvider {
	 
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private ShaPasswordEncoder sha;
	
    private static Logger logger = Logger.getLogger(DefaultAuthenticationProvider.class);
    
    private static final Set<String> setAdmin;
    static {
    setAdmin = new HashSet<String>();
    setAdmin.add("admin");
    }
 
    private boolean isAdmin(Object principal) {
        return setAdmin.contains(principal);
    }
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
 
    	
        Object principal = authentication.getPrincipal();
        //패스워드,패스워드 키
        Object password = sha.encodePassword((String) authentication.getCredentials(),"1234");
        
        //여러분의 Authentication, Authorization 검증 수단!
        /*if(!isAuthenticatedUser(principal, password)) {
           throw new BadCredentialsException("Username does not match for " + principal);
        }*/
        
        Map<String, Object> returnMap = isAuthenticatedUser(principal, password);
 
        if(returnMap.get("isLogin").equals(false)){
        	throw new BadCredentialsException("Username does not match for " + principal);
        }
        //인증이 성공했다면...
        Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
 
        if(isAdmin(principal)) {
        	roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return new UsernamePasswordAuthenticationToken(principal, password, roles);  //토큰 발급 해당 토큰은 Spring-Security에서 SecurityContext로 할당 된다.
    }
 
    private Map<String, Object> isAuthenticatedUser(Object principal, Object password) {
		// TODO 여기서 디비 연동을 하던지 말던지 알아서
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	
    	paramMap.put("USER_ID", principal);
    	//paramMap.put("PASSWD", password);
    	
    	//
    	Map<String, Object> resultMap = this.commonDao.selectToMap("com.skoh.sample.common.loginUser", paramMap);
		
    	if(resultMap == null){
    		resultMap = new HashMap<String, Object>();
    		resultMap.put("coke", "ok");
    		resultMap.put("msg", "User Id is Empty");
    		resultMap.put("isLogin", false);
    	}else{
    		
    		if(password == "" || password.toString().length() == 0){
    			resultMap = new HashMap<String, Object>();
    			resultMap.put("coke", "ok");
        		resultMap.put("msg", "User Password not Input");
        		resultMap.put("isLogin", false);
    		}
    		
    		paramMap.put("PASSWD", password);

    		resultMap = this.commonDao.selectToMap("com.skoh.sample.common.loginUserPwdCheck", paramMap);
    		
    		if(resultMap == null){
    			resultMap = new HashMap<String, Object>();
        		resultMap.put("coke", "ok");
        		resultMap.put("msg", "User Password is not Valid");
        		resultMap.put("isLogin", false);
        	}else{
        		resultMap.put("coke", "ok");
        		resultMap.put("msg", "Login Success");
        		resultMap.put("isLogin", true);
        	}
    	}

    	return resultMap;
    	
    	
	}

	@Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UsernamePasswordAuthenticationToken.class);
    }
}
