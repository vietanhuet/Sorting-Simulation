package com.via.activities;


import uet.via.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.via.gifmoviewview.GifMovieView;
import com.via.handle.SplashThread;

public class SplashActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_splash);
		
		final GifMovieView gif1 = (GifMovieView) findViewById(R.id.progress_bar);
		gif1.setMovieResource(R.drawable.progress_bar);
		SplashThread splash = new SplashThread(SplashActivity.this);
		splash.start();
	}
 
	public void onGifClick(View v) {
		GifMovieView gif = (GifMovieView) v;
		gif.setPaused(!gif.isPaused());
	}
}
