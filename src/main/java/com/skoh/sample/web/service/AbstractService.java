package com.skoh.sample.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.skoh.sample.common.dao.CommonDao;

/**
 * 서비스 껍데기
 * @author JK
 *
 */
public abstract class AbstractService{
	@Autowired
	protected CommonDao commonDao;
	/*
	 * 서비스 리턴 키값
	 */
	protected static final String SERVICE_STATUS = "status";
}
