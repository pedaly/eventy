package com.app.eventy.dao;

import android.content.Context;
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
				+ " (" + ID + " INTEGER AUTOINCREMENT PRIMARY KEY, "
				+ LNG + " REAL, "
				+ LAT + " REAL, "
				+ TITLE + " TEXT, "
				+ DESCRIPTION + " TEXT, "
				+ DATE_OF_EVENT + " TEXT, "
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
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

}
