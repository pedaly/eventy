package com.app.eventy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
			}
		}, 2500);
		
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		finish();
	}
	
}
