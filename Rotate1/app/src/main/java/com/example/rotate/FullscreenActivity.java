package com.example.rotate;



import com.example.rotate.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

public class FullscreenActivity extends Activity implements OnClickListener,ServiceConnection{
	private static final boolean AUTO_HIDE = true;
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
	private static final boolean TOGGLE_ON_CLICK = true;
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
	private SystemUiHider mSystemUiHider;
	private boolean mIsBound = false;
	ImageButton exit;
	Button button1;
	// Saves the binding instance with the service.
	private MusicService mServ;
	CheckBox cB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fullscreen);
		 if( getIntent().getBooleanExtra("Exit me", false)){
		        finish();
		        
		        return; // add this to prevent from doing unnecessary stuffs
		    }
		Intent music = new Intent(this, MusicService.class);
		startService(music);

		doBindService();

		// implementing the interface methods onClickListener
		cB =(CheckBox) findViewById(R.id.btn_pause);
		cB.setOnClickListener(this) ;
		exit =(ImageButton) findViewById(R.id.exit);
		exit.setOnClickListener(this) ;
		button1 =(Button) findViewById(R.id.button1);
		button1.setOnClickListener(this) ;
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
	public void onServiceConnected(ComponentName name, IBinder binder)
	{
		mServ = ((MusicService.ServiceBinder) binder).getService();
		mServ.start();
	}

	public void onServiceDisconnected(ComponentName name)
	{
		mServ.stop();
		mServ = null;
		
	}

	// local methods used in connection/disconnection activity with service.

	public void doBindService()
	{
		// activity connects to the service.
 		Intent intent = new Intent(this, MusicService.class);
		bindService(intent, this, Context.BIND_AUTO_CREATE);
		mIsBound = true;
	}

	public void doUnbindService()
	{
		// disconnects the service activity.
		if(mIsBound)
		{
			unbindService(this);
      		mIsBound = false;
		}
	}
	// when closing the current activity, the service will automatically shut down(disconnected).
	@Override
	public void onDestroy()
	{
		super.onDestroy();

		doUnbindService();
	}
	// interface buttons that call methods of service control on the activity.
	
	@Override
	public void onClick(View v) {
		Intent i;
		if(v.getId()==R.id.btn_pause)
		{
			if(cB.isChecked())
			{
	
				mServ.pause();
				
			
			}
			else
			{
				mServ.start();
		

			}
		
		}		
		else if(v.getId()==R.id.exit)
		{
			mServ.stop();
			onDestroy();
			i = new Intent(this,FullscreenActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    i.putExtra("Exit me", true);
		    startActivity(i);
		    finish();
		
		}
		else if(v.getId()==R.id.button1)
		{
			
			i=new Intent(this,com.example.rotate.N.class);
			startActivity(i);
		}
	}
	// menu default
	
}
