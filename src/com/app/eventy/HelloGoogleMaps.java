package com.app.eventy;

import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.app.eventy.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class HelloGoogleMaps extends MapActivity {
	public static final String PREFS_NAME = "MyPrefsFile";
	public static final int MENU_SETTING_ID = 1;
	public static final int MENU_EXIT_ID = 2;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    
	    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

	     if(settings.getBoolean("firstRun", true)){
	    	 SharedPreferences.Editor editor = settings.edit();
	         editor.putBoolean("firstRun", false);
	         editor.commit();
	         Intent intent = new Intent(this, SettingsActivity.class);
	         startActivity(intent);
	         
	     }
	 
	     

	    
	    
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
	    HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable, this);
	    
	    GeoPoint point = new GeoPoint(19240000,-99120000);
	    OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
	
	    itemizedoverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedoverlay);
	    
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
	     	menu.add(Menu.NONE,MENU_SETTING_ID,Menu.NONE,"Ustawienia");
	     	menu.add(Menu.NONE,MENU_EXIT_ID,Menu.NONE,"Wyjœcie");
	        
	    	return true;
	    }
	 
	  @Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	    	//check selected menu item
	    	if(item.getItemId() == MENU_EXIT_ID)
	    	{
	    		//close the Activity
	    		this.finish();
	    		return true;
	    	} else if(item.getItemId() == MENU_SETTING_ID){
	    		 Intent intent = new Intent(this, SettingsActivity.class);
		         startActivity(intent);
	    		return true;
	    	}
	    	return false;
	    }
}
