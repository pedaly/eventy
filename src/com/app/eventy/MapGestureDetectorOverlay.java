package com.app.eventy;

import android.location.Location;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;



import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MapGestureDetectorOverlay extends Overlay implements OnGestureListener {
	 private GestureDetector gestureDetector;
	 private OnGestureListener onGestureListener;
	 private MainActivity mainActivity;
	

	 public MapGestureDetectorOverlay(MainActivity mainActivity) {
		 gestureDetector = new GestureDetector(this);
		 this.mainActivity=mainActivity;
		 
	 }

	 public MapGestureDetectorOverlay(OnGestureListener onGestureListener) {
	
	  setOnGestureListener(onGestureListener);
	 }

	 @Override
	 public boolean onTouchEvent(MotionEvent event, MapView mapView) {
	  if (gestureDetector.onTouchEvent(event)) {
	   return true;
	  }
	  return false;
	 }

	 
	 public boolean onDown(MotionEvent e) {
	  if (onGestureListener != null) {
	   return onGestureListener.onDown(e);
	  }
	  return false;
	 }
	 
	

	
	 public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
	  if (onGestureListener != null) {
	   return onGestureListener.onFling(e1, e2, velocityX, velocityY);
	  }
	  return false;
	 }

	
	 public void onLongPress(MotionEvent e) {
		GeoPoint geoPoint=mainActivity.getMapView().getProjection().fromPixels((int)e.getX(),(int)e.getY());
		mainActivity.getLastLocation().setLatitude(geoPoint.getLatitudeE6()/1e6);
		mainActivity.getLastLocation().setLongitude(geoPoint.getLongitudeE6()/1e6);
		mainActivity.update();
	
	
	 }

	
	 public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
	   float distanceY) {
	  if (onGestureListener != null) {
	   onGestureListener.onScroll(e1, e2, distanceX, distanceY);
	  }
	  return false;
	 }


	 public void onShowPress(MotionEvent e) {
	  if (onGestureListener != null) {
	   onGestureListener.onShowPress(e);
	  }
	 }
	
	 public boolean onSingleTapUp(MotionEvent e) {
	  if (onGestureListener != null) {
	   onGestureListener.onSingleTapUp(e);
	  }
	  return false;
	 }

	 public boolean isLongpressEnabled() {
	  return gestureDetector.isLongpressEnabled();
	 }

	 public void setIsLongpressEnabled(boolean isLongpressEnabled) {
	  gestureDetector.setIsLongpressEnabled(isLongpressEnabled);
	 }

	 public OnGestureListener getOnGestureListener() {
	  return onGestureListener;
	 }

	 public void setOnGestureListener(OnGestureListener onGestureListener) {
	  this.onGestureListener = onGestureListener;
	 }

	

	

	
	}