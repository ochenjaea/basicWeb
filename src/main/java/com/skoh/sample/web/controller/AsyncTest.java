package com.skoh.sample.web.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author 오승규
 *	스프링 비동기 클래스 
 */
@Service
public class AsyncTest {

	
	
	@Async
	public void asyncTest() throws InterruptedException{
		
		
		for(int i=0;i<10;i++){
			Thread.sleep(1000);
			System.out.println("test");
		}
		
	}
}
