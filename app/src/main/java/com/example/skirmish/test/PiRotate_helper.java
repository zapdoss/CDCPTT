package com.example.skirmish.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PiRotate_helper extends RelativeLayout implements OnGestureListener {

	private GestureDetector gestureDetector;
	//private float 				mAngleDown , mAngleUp;
	private ImageView ivRotor;
	private Bitmap bmpRotorOff;
	//private boolean 			mState = false;
	private int					m_nWidth = 0, m_nHeight = 0;
	public float x,y;
	private boolean movable;
	private float a=360,min,max;
	private int per;
	
	interface PiRotateListener {
		public void onStateChange(boolean newstate) ;
		public void onRotate(int percentage);
	}
	
	private PiRotateListener m_listener;
	
	public void SetListener(PiRotateListener l) {
		m_listener = l;
	}

	
	public PiRotate_helper(Context context, int initial, int rotoroff, final int w, final int h, boolean movable) {
		super(context);
		// we won't wait for our size to be calculated, we'll just store out fixed size
		this.per = initial;
		m_nWidth = w; 
		m_nHeight = h;
		Bitmap srcoff = BitmapFactory.decodeResource(context.getResources(), rotoroff);
	    float scaleWidth = ((float) w) / srcoff.getWidth();
	    float scaleHeight = ((float) h) / srcoff.getHeight();
	    Matrix matrix = new Matrix();
	    matrix.postScale(scaleWidth, scaleHeight);
		    
		/*bmpRotorOn = Bitmap.createBitmap(
				srcon, 0, 0, 
				srcon.getWidth(),srcon.getHeight() , matrix , true);*/
		bmpRotorOff = Bitmap.createBitmap(
				srcoff, 0, 0, 
				srcoff.getWidth(),srcoff.getHeight() , matrix , true);
		// create rotor
		ivRotor = new ImageView(context);
		ivRotor.setImageBitmap(bmpRotorOff);
		RelativeLayout.LayoutParams lp_ivKnob = new RelativeLayout.LayoutParams(w,h);//LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp_ivKnob.addRule(RelativeLayout.CENTER_IN_PARENT);
		addView(ivRotor, lp_ivKnob);
		// set initial state
		//SetState(mState);
		// enable gesture detector
		this.movable=movable;
		gestureDetector = new GestureDetector(getContext(), this);
	}
	
	/**
	 * math..
	 * @param x
	 * @param y
	 * @return
	 */
	private float cartesianToPolar(float x, float y) {
		return (float) -Math.toDegrees(Math.atan2(x-0.5f  , y-0.5f ));
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if(movable){
			float x = event.getX() / ((float) getWidth());
			this.x = x;
			float y = event.getY() / ((float) getHeight());
			this.y=y;
			float rotDegrees = cartesianToPolar(1 - x, 1 - y);// 1- to correct our custom axis direction
			//float posDegrees=0;
			if (!Float.isNaN(rotDegrees)) {
                //instead of getting 0-> 180, -180 0 , we go for 0 -> 360
                //posDegrees = rotDegrees;
                //if (rotDegrees < 0) posDegrees = 360 + rotDegrees;
                // }//}*/
                //if (m_listener != null) m_listener.onRotate((int)this.a);
            }
			if(Math.abs(rotDegrees-this.a)<20){
				if (gestureDetector.onTouchEvent(event)) /*&& Math.abs(posDegrees - this.a)<50*/
					return true;
				else return super.onTouchEvent(event);}
			else return super.onTouchEvent(event);}
		else return super.onTouchEvent(event);
	}

	
	public boolean onDown(MotionEvent event) {
		return true;
	}
	
	public boolean onSingleTapUp(MotionEvent e) {
		return true;
	}

	public void setRotorPosAngle(float deg) {

		//if (deg >= 210 || deg <= 150) {
			if (deg > 180) deg = deg - 360;
			this.a = deg;
			/*Matrix matrix=new Matrix();
			ivRotor.setScaleType(ScaleType.MATRIX);
			matrix.postRotate((float) deg, getWidth()/2, getHeight()/2); // 210/2,210/2);////getWidth()/2, getHeight()/2);
			ivRotor.setImageMatrix(matrix);*/
	}
	
	public void setRotorPercentage(int percentage) {
		double posDegree = percentage * 3.6 - 180;
		this.per=percentage;
		if (posDegree < 0) posDegree = 360 + posDegree;
		setRotorPosAngle((float)posDegree);
	}
	
	
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		float x = e2.getX() / ((float) getWidth());
		float y = e2.getY() / ((float) getHeight());
		float rotDegrees = cartesianToPolar(1 - x, 1 - y);// 1- to correct our custom axis direction

		if (!Float.isNaN(rotDegrees)) {
			// instead of getting 0-> 180, -180 0 , we go for 0 -> 360
			float posDegrees = rotDegrees;
			if (rotDegrees < 0) posDegrees = 360 + rotDegrees;

			// deny full rotation, start start and stop point, and get a linear scale
			if (rotDegrees > min && rotDegrees < max) {
				// rotate our imageview
				setRotorPosAngle(posDegrees);
				// get a linear ro false;
				float scaleDegrees = rotDegrees + 180; // given the current parameters, we go from 0 to 300
				// get position percent
				int percent = (int) (scaleDegrees / 3.6);
				this.per = percent;
				if (m_listener != null) m_listener.onRotate(percent);
					return true;//consume
			}
			else
				return false;
		}
		else
			return false;
	}

	public void setMax(float m){
		this.max=m;
	}

	public void setMin(float m){
		this.min=m;
	}

	public float getRotorPosAngle(){
		return a;
	}

	public int getP(){
		return per;
	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) { return false; }

	public void onLongPress(MotionEvent e) {	}

	



}
