package com.app.eventy.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CategoryDAO extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "events";
	private static final int DATABASE_VERSION = 1;
	
	private static final String CATEGORY_TABLE_NAME = "category";	

	private static final String ID = "_id";
	private static final String NAME = "name";
	
	public CategoryDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createSQL = "CREATE TABLE " + CATEGORY_TABLE_NAME
				+ " (" + ID + " INTEGER AUTOINCREMENT PRIMARY KEY, "
				+ NAME + " TEXT)";
		db.execSQL(createSQL);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

}
