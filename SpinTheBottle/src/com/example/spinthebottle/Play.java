package com.example.spinthebottle;



import java.util.Random;

import com.example.spinthebottle.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.RotateAnimation;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
public class Play extends Activity implements OnClickListener {
	ImageButton back;
	ImageButton exit;
	ImageButton dare;
	ImageButton truth;
	ImageButton alldare;
	ImageButton alltruth;
	ImageButton spin;
	ImageView imageView;
	EditText result;
	RotateAnimation animation;
	int degrees;
	Random r;
	Resources res;
	Toast toast;
	PopupMenu pop;
	PopupWindow window;
	String q1,q;
	int previousDegrees;
	private static final boolean AUTO_HIDE = true;
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
	private static final boolean TOGGLE_ON_CLICK = true;
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
	private SystemUiHider mSystemUiHider;
	private String[] myString;
    private static final Random rgenerator = new Random();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_play);
		back =(ImageButton) findViewById(R.id.back);
		back.setOnClickListener(this) ;
		exit =(ImageButton) findViewById(R.id.exit);
		exit.setOnClickListener(this) ;
		alldare =(ImageButton) findViewById(R.id.alldare);
		alldare.setOnClickListener(this) ;
		alltruth =(ImageButton) findViewById(R.id.alltruth);
		alltruth.setOnClickListener(this) ;
		truth =(ImageButton) findViewById(R.id.truth);
		truth.setOnClickListener(this) ;
		dare =(ImageButton) findViewById(R.id.dare);
		dare.setOnClickListener(this) ;
		spin =(ImageButton) findViewById(R.id.spin);
		spin.setOnClickListener(this) ;
				
		
		
		
		
		imageView = (ImageView)findViewById(R.id.imageView2);
		
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
		result =(EditText) findViewById(R.id.result);
		res = getResources();
		Intent i;
		switch(arg0.getId())
		{
		case R.id.back:
			i=new Intent(this,com.example.spinthebottle.Menu.class);
			startActivity(i);
			break;
		case R.id.exit:
			i = new Intent(this,Home.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    i.putExtra("Exit me", true);
			    startActivity(i);
			    finish();
			break;
		case R.id.alldare:
			i=new Intent(this,com.example.spinthebottle.Dare.class);
			startActivity(i);
			break;
		case R.id.alltruth:
			i=new Intent(this,com.example.spinthebottle.Truth.class);
			startActivity(i);
			break;
		case R.id.spin:
			previousDegrees = 0;
			r = new Random();
			animation = new RotateAnimation(previousDegrees,degrees,150,150);
			animation.setDuration(1000);//Set the duration of the animation to 1 sec.
			animation.setFillEnabled(true);
			animation.setFillAfter(true);
			imageView.startAnimation(animation);
			degrees=r.nextInt(1080-720) + 720;
			
			
			myString = res.getStringArray(R.array.truth); 
		    q = myString[rgenerator.nextInt(myString.length)];
		    result.setText("Select Truth or Dare");
			
			break;
		case R.id.truth:
		    myString = res.getStringArray(R.array.truth); 
		    q = myString[rgenerator.nextInt(myString.length)];
		    result.setText(q);
			
			break;
		case R.id.dare:
		    myString = res.getStringArray(R.array.dare); 
		    q = myString[rgenerator.nextInt(myString.length)];
		    result.setText(q);
			break;
		}
		
	}
}
