package softechdeveloper.love_calculator;

import softechdeveloper.love_calculator.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
public class Test extends Activity implements OnClickListener{
	ImageButton calculate;
	ImageButton quotes;
	EditText firstname,secondname,result;
	ImageButton exit;
	private static final boolean AUTO_HIDE = true;
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
	private static final boolean TOGGLE_ON_CLICK = true;
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
	private SystemUiHider mSystemUiHider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_test);
		calculate =(ImageButton) findViewById(R.id.calculate);
		calculate.setOnClickListener(this) ;
		exit =(ImageButton) findViewById(R.id.exit);
		exit.setOnClickListener(this) ;
		firstname = (EditText) findViewById(R.id.firstname);
		secondname = (EditText) findViewById(R.id.secondname);
		result = (EditText) findViewById(R.id.result);
		quotes =(ImageButton) findViewById(R.id.quotes);
		quotes.setOnClickListener(this) ;
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
	@Override
	public void onClick(View arg0)
	{
		Intent i;
		switch(arg0.getId())
		{
	
		case R.id.calculate:
			String first = firstname.getText().toString();
			String second = secondname.getText().toString();
			if(first.equals(null)||first.equals(""))
			{
				Toast toast = Toast.makeText(this,"enter firstname",Toast.LENGTH_SHORT);
				toast.show();
			}
			
			else if(second.equals(null)||second.equals(""))
			{
				Toast toast = Toast.makeText(this,"enter secondname",Toast.LENGTH_SHORT);
				toast.show();
			}
			else if(first.equals(second))
			{
				Toast toast = Toast.makeText(this,"both names cannot be same",Toast.LENGTH_SHORT);
				toast.show();
			}
			else
			{
				int f=first.length();
				int s=second.length();
				int r=s*f+50;
				if(r<=100)
				{
					result.setText(String.valueOf(r)+"%");
				}
				else if(r>=100 && r<=200)
				{
					r=r-100;
					result.setText(String.valueOf(r)+"%");
				}
				else if(r>200 && r<=300)
				{
					r=r-200;
					result.setText(String.valueOf(r)+"%");
				}
				else if(r>300 && r<=400)
				{
					r=r-300;
					result.setText(String.valueOf(r)+"%");
				}
				else if(r>400 && r<=500)
				{
					r=r-400;
					result.setText(String.valueOf(r)+"%");
				}
				else if(r>500 && r<=600)
				{
					r=r-500;
					result.setText(String.valueOf(r)+"%");
				}
				else if(r>600 && r<=700)
				{
					r=r-600;
					result.setText(String.valueOf(r)+"%");
				}
				else if(r>700 && r<=800)
				{
					r=r-700;
					result.setText(String.valueOf(r)+"%");
				}
				else if(r>800 && r<=900)
				{
					r=r-800;
					result.setText(String.valueOf(r)+"%");
				}
			}
			
			break;
		case R.id.exit:
			i = new Intent(this,Home.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    i.putExtra("Exit me", true);
		    startActivity(i);
		    finish();
		    break;
		case R.id.quotes:
			i=new Intent(this,softechdeveloper.love_calculator.LoveQuotes.class);
			startActivity(i);
			break;
			
		}
		
	}

}
