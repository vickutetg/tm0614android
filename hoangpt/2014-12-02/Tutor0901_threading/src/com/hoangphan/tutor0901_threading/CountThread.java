package com.hoangphan.tutor0901_threading;

public class CountThread extends Thread {
	
	long count = 0;
	MainActivity act;
	
	public CountThread(MainActivity act) {
		this.act = act;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				count++;
				act.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						act.txtCount.setText(""+count);
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
