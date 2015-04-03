package com.example.musicplayer;



import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private MediaPlayer mediaPlayer;
	private TextView startTimeTextView;
	private TextView endTimeTextView;
	private TextView songNameTextView;
	private ImageButton previousBtnImageButton;
	private ImageButton rrBtnImageButton;
	private ImageButton playBtnImageButton;
	private ImageButton pauseBtnImageButton;
	private ImageButton ffBtnImageButton;
	private ImageButton nextBtnImageButton;
	private SeekBar songSeekBar;
	private double duration = 0.00;
	private double start = 0.00;
	private int fwd = 2500;
	private int back = 2500;
	private Handler handle = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mediaPlayer = MediaPlayer.create(this, R.raw.awesome);
		startTimeTextView = (TextView) findViewById(R.id.startTimeTextView);
		endTimeTextView = (TextView) findViewById(R.id.endTimeTextView);
		songNameTextView = (TextView) findViewById(R.id.songNameTextView);
		songSeekBar = (SeekBar) findViewById(R.id.songSeekBar);
		//start = mediaPlayer.getDuration();
		duration = mediaPlayer.getDuration(); 
		songSeekBar.setMax((int) start);
		songSeekBar.setClickable(false);
		songNameTextView.setText(R.raw.awesome);
		previousBtnImageButton = (ImageButton) findViewById(R.id.previousBtnImageButton);
		nextBtnImageButton = (ImageButton) findViewById(R.id.nextBtnImageButton);
		ffBtnImageButton = (ImageButton) findViewById(R.id.ffBtnImageButton);
		rrBtnImageButton = (ImageButton) findViewById(R.id.rrBtnImageButton);
		playBtnImageButton = (ImageButton) findViewById(R.id.playBtnImageButton);
		pauseBtnImageButton = (ImageButton) findViewById(R.id.pauseBtnImageButton);
		
		songSeekBar.setOnSeekBarChangeListener(seekBarListener);
		
		ffBtnImageButton.setOnClickListener(new View.OnClickListener() {
			 
	        @Override
	        public void onClick(View v) {
	            
	            int now = mediaPlayer.getCurrentPosition();
	            
	            if(now + fwd <= duration){
	                
	            	mediaPlayer.seekTo(now + fwd);
	            	updateSeekBar();
	            }else{
	                
	            	mediaPlayer.seekTo(mediaPlayer.getDuration());
	            	updateSeekBar();
	            }
	        }
	    });
		
		playBtnImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mediaPlayer.start();
				start = mediaPlayer.getCurrentPosition();
				songSeekBar.setProgress((int) start);
				updateSeekBar();
				
			}

			
		});
		
		
		pauseBtnImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mediaPlayer.pause();
				
			}
		});
		
	
	
	rrBtnImageButton.setOnClickListener(new View.OnClickListener() {
		 
        @Override
        public void onClick(View v) {
            
            int now = mediaPlayer.getCurrentPosition();
            
            if(now - fwd > 0){
                
            	mediaPlayer.seekTo(now - fwd);
            	updateSeekBar();
            }else{
                
            	mediaPlayer.seekTo(0);
            	updateSeekBar();
            }
        }
    });
	
	}
	
		protected void updateSeekBar() {
		handle.postDelayed(updateTime, 100);
	}
		
		private Runnable updateTime = new Runnable() {
			public void run() {
				int now = mediaPlayer.getCurrentPosition();
				//startTimeTextView.setText(now);
				songSeekBar.setProgress((int) now);
				
				double duration = mediaPlayer.getDuration() - now;
				//endTimeTextView.setText(Double.toString(duration));
				handle.postDelayed(this, 100);
			}
		};


		private OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			int now = mediaPlayer.getCurrentPosition();
			mediaPlayer.seekTo(now);
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
