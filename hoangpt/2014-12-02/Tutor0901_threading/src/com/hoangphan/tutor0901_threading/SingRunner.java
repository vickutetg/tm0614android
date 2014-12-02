package com.hoangphan.tutor0901_threading;

public class SingRunner implements Runnable {
	
	public SingRunner() {
		//new Thread, wrapper
		new Thread(this).start();
	}

	@Override
	public void run() {
	}

}
