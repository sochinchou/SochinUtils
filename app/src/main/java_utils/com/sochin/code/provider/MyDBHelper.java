package com.sochin.code.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper {
	private static final String TAG = "MyDBHelper";

	public static final String KEY_ID = "_id";
	public static final String KEY_TITLE = "key_title";
	public static final String KEY_VALUE = "key_value";

	private final Context mContext;
	private MyOpenHelper mOpenHelper;
	private SQLiteDatabase mDB;

	public MyDBHelper(Context ctx) {
		mContext = ctx;
		mOpenHelper = new MyOpenHelper(mContext);
	}

	public MyDBHelper open() throws SQLException {
		Log.d(TAG, "open() >>>>>");
		mDB = mOpenHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		Log.d(TAG, "close() >>>>>");
		mOpenHelper.close();
	}

	
	
	public long insertItem1() {
		ContentValues initialValues = new ContentValues();
		return mDB.insert(DATABASE_TABLE, null, initialValues);
	}
	
	public long insertItem2() {
		ContentValues initialValues = new ContentValues();
		return mDB.insert(DATABASE_TABLE, KEY_TITLE, initialValues);
	}
	
	public long insertItem3() {
		ContentValues initialValues = new ContentValues();
		String str = null;
		initialValues.put(KEY_TITLE, str);
		return mDB.insert(DATABASE_TABLE, null, initialValues);
	}
	

	
	public long insertItem(String title, String content) {
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_VALUE, content);
		return mDB.insert(DATABASE_TABLE, null, values);
	}
	
	public boolean updateItem(long rowId, String title, String content) {
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_VALUE, content);
		return mDB.update(DATABASE_TABLE, values, KEY_ID + "=" + rowId, null) > 0;
	}

	public boolean deleteItem(long rowId) {
		return mDB.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) > 0;
	}
	
	public boolean clearItems(){
		return mDB.delete(DATABASE_TABLE, null, null) > 0;
	}

	public Cursor getItem(long rowId){
		return mDB.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_TITLE, KEY_VALUE }, KEY_ID + "=" + rowId, null, null, null, null);
	}
	
	public Cursor getAllItems() {
		return mDB.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_TITLE, KEY_VALUE }, null, null, null, null, null);
	}




	
	// ************************************************************
	// DatabaseHelper
	// ************************************************************
	private static final String DATABASE_NAME = "my_db";
	private static final String DATABASE_TABLE = "my_table";
	private static final int DATABASE_VERSION = 1;

	private static class MyOpenHelper extends SQLiteOpenHelper {

		MyOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			Log.d(TAG, "[MyOpenHelper] MyOpenHelper() >>>");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d(TAG, "[MyOpenHelper] onCreate() >>>");
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" 
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_TITLE + " TEXT,"
			+ KEY_VALUE + " TEXT);");	
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.d(TAG, "[MyOpenHelper] onUpgrade() >>> update database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}
	
	

}
