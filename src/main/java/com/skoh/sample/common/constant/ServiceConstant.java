package com.skoh.sample.common.constant;

/**
 * 서비스
 * @author semoria
 *
 */
public interface ServiceConstant {
	public static final String SELECT = "select";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String LIST = "list";
	public static final String INSERT = "insert";
	
	// 실제 쿼리 ID
	public static final String SERVICE_QUERY_ID = "QUERYID";
	// 데이터가 없을때 데이터 자체가 없는지 확인하는 쿼리 ID
	public static final String SERVICE_EMPTY_QUERY = "EMPTY_QUERYID";
	
	// 수행 명령(수정, 삭제 등)
	public static final String SERVICE_ACTION = "ACTION";
	
	public static final String SERVICE_RESPONSENAME = "RESPONSEID";
	
	
	public static final String BOARDIU = "boardiu";
	
	public static final Integer SUCESS = 1;
	public static final Integer FAIL = 2;
	public static final Integer DUPLICATE = 10;
	
}
