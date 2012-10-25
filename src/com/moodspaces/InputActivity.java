package com.moodspaces;

import java.util.ArrayList;
import java.util.List;

import com.moodspaces.model.MoodData;
import com.moodspaces.model.MoodSelection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Activity that allows the user to enter their mood, location and current activity.
 * 
 * @author Geert Van Campenhout
 */
public class InputActivity extends Activity implements OnTouchListener {

	private ImageView imageView;
	private Bitmap bitmap;
	private Canvas canvas;
	private Paint paint;
	private int color1 = Color.rgb(236,  231,  242);
	private int color2 = Color.rgb(43, 140, 190);
	private MoodData moodData;
	private String[] moodNames = {"Terror", "Amazement", "Grief", "Loathing", "Rage", "Vigilance", "Ecstacy", "Admiration"};
	private int[] moodColors = {Color.rgb(0,139,69), 
			Color.rgb(147, 112, 219), 
			Color.rgb(205, 50, 120), 
			Color.rgb(205, 38, 38), 
			Color.rgb(238, 121, 66), 
			Color.rgb(238, 118, 0), 
			Color.rgb(238, 173, 14), 
			Color.rgb(0, 205, 102)};
	private float padding = 20f;
	private float radius;
	private float centerX;
	private float centerY;
	private ArrayList<MoodSelection> selectedMoods;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		addItemsOnActivitySpinner();
		addItemsOnLocationSpinner();
		initPlutchikWheelImageView();
		initTouchImageView();
		moodData = new MoodData();
		selectedMoods = new ArrayList<MoodSelection>();
	}
	
	/**
	 * This method makes sure the gradient that is used in the background does not look banded.
	 */
	@Override
	public void onAttachedToWindow() {
	    super.onAttachedToWindow();
	    Window window = getWindow();
	    window.setFormat(PixelFormat.RGBA_8888);
	}
	
	/**
	 * Initialize the ImageView that contains the wheel of Plutchik.
	 */
	private void initPlutchikWheelImageView() {
		//This variable decides at what x,y position on the screen the wheel starts.
		
		ImageView wheelView = (ImageView) this.findViewById(R.id.imageView);
		Display currentDisplay = getWindowManager().getDefaultDisplay();
		float dw = currentDisplay.getWidth();		
		Bitmap wheelBitmap = Bitmap.createBitmap((int) dw, (int) dw, Bitmap.Config.ARGB_8888);
		Canvas wheelCanvas = new Canvas(wheelBitmap);
		Paint wheelPaint = new Paint();
		wheelPaint.setColor(color1);
		wheelView.setImageBitmap(wheelBitmap);
		RectF circle = new RectF(padding, padding, dw-padding, dw-padding);
		radius = circle.width()/2;
		wheelCanvas.drawOval(circle, wheelPaint);
		
		for (int i = 0; i < moodColors.length; i++) {
			wheelPaint.setColor(moodColors[i]);
			wheelCanvas.drawArc(circle, i*45, 45, true, wheelPaint);
		}
		
		wheelPaint.setColor(Color.WHITE);
		wheelPaint.setTextSize(20);
		wheelPaint.setTextAlign(Paint.Align.CENTER);
		centerX = dw/2;
		centerY = dw/2;
		float leftTextXPos = dw/4;
		float rightTextXPos = 3*dw/4;
		
		wheelCanvas.rotate(25f, centerX, centerY);
		wheelCanvas.drawText(moodNames[4], leftTextXPos, centerY, wheelPaint);
		wheelCanvas.drawText(moodNames[0], rightTextXPos, centerY, wheelPaint);
		wheelCanvas.rotate(45f, centerX, centerY);
		wheelCanvas.drawText(moodNames[5], leftTextXPos, centerY, wheelPaint);
		wheelCanvas.drawText(moodNames[1], rightTextXPos, centerY, wheelPaint);
		wheelCanvas.rotate(-90f, centerX, centerY);
		wheelCanvas.drawText(moodNames[3], leftTextXPos, centerY, wheelPaint);
		wheelCanvas.drawText(moodNames[7], rightTextXPos, centerY, wheelPaint);
		wheelCanvas.rotate(-45f, centerX, centerY);
		wheelCanvas.drawText(moodNames[2], leftTextXPos, centerY, wheelPaint);
		wheelCanvas.drawText(moodNames[6], rightTextXPos, centerY, wheelPaint);
	}

	/**
	 * Initialize the ImageView and the objects needed to draw on the imageView.
	 */
	private void initTouchImageView() {
		imageView = (ImageView) this.findViewById(R.id.realImageView);
		Display currentDisplay = getWindowManager().getDefaultDisplay();
		float dw = currentDisplay.getWidth();
		bitmap = Bitmap.createBitmap((int) dw, (int) dw, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		paint = new Paint();
		paint.setColor(color1);
		imageView.setImageBitmap(bitmap);
		imageView.setOnTouchListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_input, menu);
		return true;
	}

	/**
	 * Method that is called when the OK button is pressed.
	 */
	public void submitInput(){
		System.out.println("TODO save the input in the database here");
	}
	
	/**
	 * Method that should add the different locations to the spinner.
	 */
	private void addItemsOnLocationSpinner() {
		Spinner spinner = (Spinner) findViewById(R.id.locationSpinner);
		List<String> list = new ArrayList<String>();
		list.add("Kot");
		list.add("Thuis");
		list.add("200A");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
	}

	/**
	 * Method that should add the different activities to the spinner.
	 */
	private void addItemsOnActivitySpinner() {
		Spinner spinner = (Spinner) findViewById(R.id.activitySpinner);
		List<String> list = new ArrayList<String>();
		list.add("Mume");
		list.add("Capsel");
		list.add("Thesis");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
	}

	private float downx = 0,downy = 0, upx = 0, upy = 0;
	
	/**
	 * Called when a motionEvent happens on the screen.
	 * This method should draw and enable the movement of moods.
	 */
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		float centeredX;
		float centeredY;
		float r;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			downx = event.getX();
			downy = event.getY();
			centeredX = downx - centerX;
			centeredY = downy - centerY;
			r = (float) Math.sqrt(Math.pow(centeredX, 2) + Math.pow(centeredY, 2));
			if(r <= radius){
				//TODO hier moet gecheckt worden of er al een mood is die dichtbij is en dan die verwijderen uit te lijst zodanig dat een user een mood kan verplaatsen.
				drawMood();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			upx = event.getX();
		    upy = event.getY();
		    centeredX = upx - centerX;
			centeredY = upy - centerY;
		    bitmap.eraseColor(Color.argb(0, 1, 1, 1));
			r = (float) Math.sqrt(Math.pow(centeredX, 2) + Math.pow(centeredY, 2));
			if(r <= radius){
			    drawMood();
			    downx = upx;
			    downy = upy;
			} else{
				imageView.invalidate();
			}
			break;
		case MotionEvent.ACTION_UP:
		      upx = event.getX();
		      upy = event.getY();
		      centeredX = upx - centerX;
		      centeredY = upy - centerY;
		      bitmap.eraseColor(Color.argb(0, 1, 1, 1));
		      r = (float) Math.sqrt(Math.pow(centeredX, 2) + Math.pow(centeredY, 2));
		      if(r <= radius){
		    	  	drawMood();
		    	  	float theta = (float) Math.atan2(centeredY, centeredX);
		    	  	saveMoodSelection(r, theta);
				    downx = upx;
				    downy = upy;
		      } else{
		    	  imageView.invalidate();
		      }
		      break;
		}
		return true;
	}
	
	/**
	 * This method should save the given floats as a MoodSelection.
	 * 
	 * @param r		The r coordinate of the mood selection.
	 * @param theta	The theta coordinate of the mood selection.
	 */
	private void saveMoodSelection(float r, float theta) {
		selectedMoods.add(new MoodSelection(r, theta));
	}
	
	/**
	 * This method is called when the Ok button at the bottom of the inputActivity is clicked.
	 */
	public void saveMoodData(View view){
		//TODO save the mood data
		System.out.println("MoodData should be saved here.");
		Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
	}

	/**
	 * This method draws the Mood symbol if the clicked position is inside the image.
	 * 
	 * @param centeredX	The x coordinate with its origin lying in the center of the image.
	 * @param centeredY The y coordinate with its origin lying in the center of the image.
	 * @param r
	 */
	private void drawMood(){
		paint.setColor(color2);
		canvas.drawCircle(downx, downy, 10, paint);
		paint.setColor(color1);
		canvas.drawCircle(downx, downy, 6, paint);
		imageView.invalidate();
	}
}
