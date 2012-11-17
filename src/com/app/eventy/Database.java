package com.app.eventy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "events";
	private static final int DATABASE_VERSION = 1;
	
	private static final String TABLE_NAME = "event";
	private static final String ID = "_id";
	private static final String LNG = "lng";
	private static final String LAT = "lat";
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String DATE_OF_EVENT = "date_of_event";
	private static final String MANAGER_OF_EVENT = "managerOfEvent";
	private static final String CITY = "city";
	private static final String STREET_AND_NUMBER = "streetAndNumber";
	private static final String WEBPAGE = "webpage";
	private static final String PHONE = "phone";
	private static final String SKYPE = "skype";
	private static final String SYMBOL = "symbol";
	
	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createSQL = "CREATE TABLE " + TABLE_NAME
				+ " ( " + ID + " INTEGER AUTOINCREMENT PRIMARY KEY, "
				+ LNG + " TEXT, "
				+ LAT + " TEXT, "
				+ TITLE + " TEXT, "
				+ DESCRIPTION + " TEXT, "
				+ DATE_OF_EVENT + " TEXT, "
				+ MANAGER_OF_EVENT + " TEXT, "
				+ CITY + " TEXT, "
				+ STREET_AND_NUMBER + " TEXT, "
				+ WEBPAGE + " TEXT, "
				+ PHONE + " TEXT, "
				+ SKYPE + " TEXT, "
				+ SYMBOL + " TEXT )";
		db.execSQL(createSQL);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}


}
