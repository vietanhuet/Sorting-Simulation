package com.via.handle;

import com.via.activities.MainActivity;
import android.app.Activity;
import android.content.Intent;
public class SplashThread extends Thread {
	public SplashThread() {
	}
	public Activity parentActivity;
	public SplashThread(Activity parentActivity) {
		super();
		this.parentActivity = parentActivity;
	}
	@Override
	public void run() {
		super.run();
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Intent in = new Intent(parentActivity, MainActivity.class);
		parentActivity.startActivity(in);
		parentActivity.finish();
	}
}