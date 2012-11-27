package com.app.eventy;

import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.app.eventy.dao.EventDAO;
import com.app.eventy.model.Event;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {
	
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
	 
	     

	    
	    // Wyswietlanie eventow na mapie
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.getController().setCenter(new GeoPoint((int) (52.05 * 1E6), (int) (19.45 * 1E6)));
	    mapView.getController().setZoom(7);
	    mapView.setBuiltInZoomControls(true);
	    
	    Drawable eventMarker = this.getResources().getDrawable(R.drawable.event_marker);
	    EventItemizedOverlay eventItemizedOverlay = new EventItemizedOverlay(eventMarker, this);
	    
	    EventDAO eventDAO = new EventDAO(getApplicationContext());
	    List<Event> events = eventDAO.getAllEvents();
	    for(Event event : events) {
	    	eventItemizedOverlay.addEvent(event);
	    }
	    
	    mapView.getOverlays().add(eventItemizedOverlay);
	    //Koniec
	    
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
