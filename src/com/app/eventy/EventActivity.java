package com.app.eventy;

import com.app.eventy.dao.EventDAO;
import com.app.eventy.model.Event;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EventActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        
        int id = this.getIntent().getExtras().getInt("id");
        EventDAO eventDAO = new EventDAO(getApplicationContext());
        Event event = eventDAO.getEvent(id);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    
}
