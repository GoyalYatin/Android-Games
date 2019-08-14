package com.example.spinthebottle;

import com.example.spinthebottle.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Menu extends Activity implements OnClickListener{
	ImageButton play;
	ImageButton aboutbutton;
	ImageButton dare;
	ImageButton truth;
	ImageButton exit;
	private static final boolean AUTO_HIDE = true;
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
	private static final boolean TOGGLE_ON_CLICK = true;
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
	private SystemUiHider mSystemUiHider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu);
		play =(ImageButton) findViewById(R.id.play);
		play.setOnClickListener(this) ;
		aboutbutton =(ImageButton) findViewById(R.id.aboutbutton);
		aboutbutton.setOnClickListener(this) ;
		dare =(ImageButton) findViewById(R.id.dare);
		dare.setOnClickListener(this) ;
		truth =(ImageButton) findViewById(R.id.truth);
		truth.setOnClickListener(this) ;
		exit =(ImageButton) findViewById(R.id.exit);
		exit.setOnClickListener(this) ;
		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content_controls);
		mSystemUiHider = SystemUiHider.getInstance(this, contentView,
				HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider
				.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
		
					int mControlsHeight;
					int mShortAnimTime;

					@Override
					@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
					public void onVisibilityChange(boolean visible) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
							if (mControlsHeight == 0) {
								mControlsHeight = controlsView.getHeight();
							}
							if (mShortAnimTime == 0) {
								mShortAnimTime = getResources().getInteger(
										android.R.integer.config_shortAnimTime);
							}
							controlsView
									.animate()
									.translationY(visible ? 0 : mControlsHeight)
									.setDuration(mShortAnimTime);
						} else {
							controlsView.setVisibility(visible ? View.VISIBLE
									: View.GONE);
						}

						if (visible && AUTO_HIDE) {
		
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});

		
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					mSystemUiHider.toggle();
				} else {
					mSystemUiHider.show();
				}
			}
		});

		
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		
		delayedHide(100);
	}
	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};

	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}
	public void onClick(View arg0)
	{
		Intent i;
		switch(arg0.getId())
		{
		case R.id.play:
			i=new Intent(this,com.example.spinthebottle.Play.class);
			startActivity(i);
			break;
		case R.id.aboutbutton:
			i=new Intent(this,com.example.spinthebottle.About.class);
			startActivity(i);
			
			break;
		case R.id.dare:
			i=new Intent(this,com.example.spinthebottle.Dare.class);
			startActivity(i);
			break;
		case R.id.truth:
			i=new Intent(this,com.example.spinthebottle.Truth.class);
			startActivity(i);
			break;
		case R.id.exit:
			i = new Intent(this,Home.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    i.putExtra("Exit me", true);
			    startActivity(i);
			    finish();
			break;
		}
		
	}
}
