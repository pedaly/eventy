package com.app.eventy.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.eventy.model.Event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDAO extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "events";
	private static final int DATABASE_VERSION = 1;
	
	private static final String EVENT_TABLE_NAME = "event";
	private static final String CATEGORY_TABLE_NAME = "category";	

	private static final String ID = "_id";
	private static final String LNG = "lng";
	private static final String LAT = "lat";
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String DATE_OF_EVENT = "dateOfEvent";
	private static final String MANAGER_OF_EVENT = "managerOfEvent";
	private static final String CITY = "city";
	private static final String STREET_AND_NUMBER = "streetAndNumber";
	private static final String WEBPAGE = "webpage";
	private static final String PHONE = "phone";
	private static final String SKYPE = "skype";
	private static final String POSTCODE = "postcode";
	private static final String WOJEWODZTWO = "wojewodztwo";
	private static final String POWIAT = "powiat";
	private static final String CATEGORY = "category";
	
	public EventDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createSQL = "CREATE TABLE " + EVENT_TABLE_NAME
				+ " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ LNG + " REAL, "
				+ LAT + " REAL, "
				+ TITLE + " TEXT, "
				+ DESCRIPTION + " TEXT, "
				+ DATE_OF_EVENT + " REAL, "
				+ MANAGER_OF_EVENT + " TEXT, "
				+ CITY + " TEXT, "
				+ STREET_AND_NUMBER + " TEXT, "
				+ WEBPAGE + " TEXT, "
				+ PHONE + " TEXT, "
				+ SKYPE + " TEXT, "
				+ POSTCODE + " TEXT, "
				+ WOJEWODZTWO + " TEXT, "
				+ POWIAT + " TEXT, "
				+ CATEGORY + " INTEGER, "
				+ "FOREIGN KEY(" + CATEGORY + ") REFERENCES " + CATEGORY_TABLE_NAME + " (" + ID + "))";
		db.execSQL(createSQL);
		
		//TODO do usuniecia - na sztywno jeden event
		ContentValues content = new ContentValues();
		content.put(LNG, 19.45);
		content.put(LAT, 52.05);
		content.put(TITLE, "test");
		content.put(DESCRIPTION, "test");
		content.put(DATE_OF_EVENT, new Date().getTime());
		content.put(MANAGER_OF_EVENT, "test");
		content.put(CITY, "test");
		content.put(STREET_AND_NUMBER, "test");
		content.put(WEBPAGE, "test");
		content.put(PHONE, "test");
		content.put(SKYPE, "test");
		content.put(POSTCODE, "test");
		content.put(WOJEWODZTWO, "test");
		content.put(POWIAT, "test");
		content.put(CATEGORY, 1);
		db.insert(EVENT_TABLE_NAME, null, content);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}
	
	public List<Event> getAllEvents() {
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery("SELECT * FROM " + EVENT_TABLE_NAME, null);
		List<Event> events = new ArrayList<Event>();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			Event event = new Event();
			event.setId(cursor.getInt(cursor.getColumnIndex(ID)));
			event.setLng(cursor.getDouble(cursor.getColumnIndex(LNG)));
			event.setLat(cursor.getDouble(cursor.getColumnIndex(LAT)));
			event.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
			event.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
			event.setDateOfEvent(new Date(cursor.getLong(cursor.getColumnIndex(DATE_OF_EVENT))));
			event.setManagerOfEvent(cursor.getString(cursor.getColumnIndex(MANAGER_OF_EVENT)));
			event.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
			event.setStreetAndNumber(cursor.getString(cursor.getColumnIndex(STREET_AND_NUMBER)));
			event.setWebpage(cursor.getString(cursor.getColumnIndex(WEBPAGE)));
			event.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
			event.setSkype(cursor.getString(cursor.getColumnIndex(SKYPE)));
			event.setPostcode(cursor.getString(cursor.getColumnIndex(POSTCODE)));
			event.setWojewodztwo(cursor.getString(cursor.getColumnIndex(WOJEWODZTWO)));
			event.setPowiat(cursor.getString(cursor.getColumnIndex(POWIAT)));
			event.setCategory(cursor.getInt(cursor.getColumnIndex(CATEGORY)));
			events.add(event);
			cursor.moveToNext();
		}
		cursor.close();
		database.close();
		return events;
	}
	
	public void saveEvents(List<Event> events) {
		SQLiteDatabase database = this.getWritableDatabase();
		for(Event event : events) {
			ContentValues content = new ContentValues();
			content.put(LNG, event.getLng());
			content.put(LAT, event.getLat());
			content.put(TITLE, event.getTitle());
			content.put(DESCRIPTION, event.getDescription());
			content.put(DATE_OF_EVENT, event.getDateOfEvent().getTime());
			content.put(MANAGER_OF_EVENT, event.getManagerOfEvent());
			content.put(CITY, event.getCity());
			content.put(STREET_AND_NUMBER, event.getStreetAndNumber());
			content.put(WEBPAGE, event.getWebpage());
			content.put(PHONE, event.getPhone());
			content.put(SKYPE, event.getSkype());
			content.put(POSTCODE, event.getPostcode());
			content.put(WOJEWODZTWO, event.getWojewodztwo());
			content.put(POWIAT, event.getPowiat());
			content.put(CATEGORY, event.getCategory());
			database.insert(EVENT_TABLE_NAME, null, content);
		}
		database.close();
	}
	
	public void deleteAllEvents() {
		SQLiteDatabase database = this.getWritableDatabase();
		database.delete(EVENT_TABLE_NAME, null, null);
		database.close();
	}

}
