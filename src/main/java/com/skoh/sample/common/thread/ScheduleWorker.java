package com.skoh.sample.common.thread;

/**
 * 일반 worker와 다름. pause가 없어지고
 * 멈주거나 주기마다 돌거나 둘중 하나
 * @author semoria
 *
 */
public abstract class ScheduleWorker extends Thread {
	protected long sleepTime = 100;
	/**
	 * 실행중인가? 
	 */
	private boolean bThreadRunning_;

	/**
	 * Constructor
	 * @param name
	 */
	public ScheduleWorker(String name) {
		super("Thread-" + name); 
		this.bThreadRunning_ = true; 
	}
	/**
	 * Constructor
	 * @param name
	 * @param sleepTime
	 */
	public ScheduleWorker(String name, long sleepTime) { 
		super("Thread-" + name); 
		this.sleepTime = sleepTime;
		
		this.bThreadRunning_ = true; 
	}
	
	/**
	 * 작업시작 
	 */
	public boolean startBean() { 
		bThreadRunning_ = true; 
		return true; 
	}
	/**
	 * 작업종료
	 */
	public boolean stopBean() { 
		bThreadRunning_ = false; 
		interrupt(); 
		
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
			safeSleep(sleepTime);
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
	
	public abstract void destroy();
}