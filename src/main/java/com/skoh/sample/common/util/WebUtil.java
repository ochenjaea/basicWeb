package com.skoh.sample.common.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public class WebUtil {
	private static final String EMPTY_URL = "EMPTY_URL";
	/**
	 * Path 정
	 * @param url
	 * @return
	 */
	public static ModelAndView getModelAndView(String url) {
		url = url.replaceAll(".do", "");
		ModelAndView mav = new ModelAndView(url);
		
		String[] path = url.split("\\/");
		
		mav.addObject("pageName", path[0]);
		if(path.length == 1) {
			mav.addObject("subPageName", EMPTY_URL);
			mav.addObject("deepSubPageName", EMPTY_URL);
		}
		else if(path.length == 2) {
			mav.addObject("subPageName", path[1]);
			mav.addObject("deepSubPageName", EMPTY_URL);
		}
		else if(path.length == 3) {
			mav.addObject("subPageName", path[1]);
			mav.addObject("deepSubPageName", path[2]);
		}
		
		return mav;
	}
	/**
	 * URI에서 요청 파일이름 추출
	 * @param uri
	 * @return
	 */
	public static String getURItoFileName(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		int begin = 0;
		if ((contextPath == null) || (contextPath.equals(""))) {
			begin = 1;
		} else {
			begin = contextPath.length() + 1;
		}
		
		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}
		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		
		return fileName.replace("pageView/", "");
	}
	/**
	 * 케릭터 인코딩
	 * @param inp
	 * @return
	 */
	public static String unescape(String inp) {
       String rtnStr = new String();
       char [] arrInp = inp.toCharArray();
       int i;
       for(i=0;i<arrInp.length;i++) {
           if(arrInp[i] == '%') {
               String hex;
               if(arrInp[i+1] == 'u') {    //유니코드.
                   hex = inp.substring(i+2, i+6);
                    i += 5;
                } else {    //ascii
                    hex = inp.substring(i+1, i+3);
                    i += 2;
                }
                try{
                    rtnStr += new String(Character.toChars(Integer.parseInt(hex, 16)));
                } catch(NumberFormatException e) {
                    rtnStr += "%";
                    i -= (hex.length()>2 ? 5 : 2);
                }
            } else {
                rtnStr += arrInp[i];
            }
        }

        return rtnStr;
    }
	/**
	 * 문자열 인코딩
	 * @param str
	 * @return
	 */
	public static String getEnCodingString(String str) {
		try {
			str = new String(str.getBytes("8859_1"), "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void main(String[] args) {
		String test = "test/123/4444";
		System.out.println(test.split("\\/").length);
	}
}
