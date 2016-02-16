package com.skoh.sample.common.thread;

/**
 * worker
 * @author gonnns
 *
 */
public abstract class Worker extends Thread {
	protected long sleepTime = 100;
	/**
	 * 실행중인가? 
	 */
	private boolean bThreadRunning_;
	/**
	 * 일시 중지 상태인가?
	 */
	private boolean bThreadPaused_;

	/**
	 * Constructor
	 * @param name
	 */
	public Worker(String name) {
		super("Thread-" + name); 
		this.bThreadRunning_ = true; 
		this.bThreadPaused_ = true;
	}
	/**
	 * Constructor
	 * @param name
	 * @param sleepTime
	 */
	public Worker(String name, int sleepTime) { 
		super("Thread-" + name); 
		this.sleepTime = sleepTime;
		
		this.bThreadRunning_ = true; 
		this.bThreadPaused_ = true;
	}
	
	/**
	 * 작업시작 
	 */
	public boolean startBean() { 
		bThreadRunning_ = true; 
		bThreadPaused_ = false; 
		return true; 
	}
	/**
	 * 작업종료
	 */
	public boolean stopBean() { 
		bThreadRunning_ = false; 
		bThreadPaused_ = false;
		interrupt(); 
		
		return true; 
	}
	/**
	 * 작업 일시중단 
	 */
	public boolean pauseBean() {
		bThreadRunning_ = true; 
		bThreadPaused_ = true; 
		return true; 
	}
	/**
	 * 잠자기 
	 * @param time
	 * @return true : 정상적으로 잠자고 일어났음, false : 자다 깼음 
	 */
	protected boolean safeSleep(long time) { 
		try { 
			if(time <= 0) {
				time = sleepTime;
			}
			Thread.sleep(time); 
		}
		catch(InterruptedException ex) { 
			return false; 
		}
		
		return true; 
	}
	
	/**
	 * 작업하기 
	 */
	@Override 
	public void run() { 
		while(isRunning()) { 
			if(isPaused()) {
				safeSleep(sleepTime);
				continue; 					
			}
			working(); 
		}
	}
	/**
	 * 처리할 작업 
	 */
	protected abstract void working(); 
	
	/**
	 * 동작 중인가? 
	 * @return true - 동작중, false - 동작하지 않음
	 */
	public boolean isRunning() { 
		return bThreadRunning_; 
	}
	/**
	 * 일시중지 상태인가? 
	 * @return true - 일시정지중, false - 실행중 혹은 정지됨
	 */
	public boolean isPaused() { 
		return bThreadPaused_; 
	}
	
	public abstract void destroy();
}