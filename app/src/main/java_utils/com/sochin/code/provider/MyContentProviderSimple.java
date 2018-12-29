package com.sochin.code.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

public class MyContentProviderSimple extends ContentProvider {
	public static final String TAG = "MyContentProviderSimple";
	
	public static final String AUTHORITY = "com.sochinchou.provider";
	private static final UriMatcher uriMatcher;
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "navi", DBItem.ITEMS);
		uriMatcher.addURI(AUTHORITY, "navi/#", DBItem.ITEM_ID);
	}

	
	// --------------------------- Database Helper

    private static class DatabaseHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "knowledge_database.db";
        private static final int DATABASE_VERSION = 1;
       
		  DatabaseHelper(Context context) {
		   super(context, DATABASE_NAME, null, DATABASE_VERSION);
		  }
		  
		  @Override
		  public void onCreate(SQLiteDatabase db) {
		   db.execSQL("CREATE TABLE " + DBItem.TABLE_NAME + " ("
		     + DBItem._ID + " INTEGER PRIMARY KEY,"
		     + DBItem.TITLE + " TEXT,"
		     + DBItem.CONTENT+ " TEXT,"
		     + DBItem.TIME + " TEXT"
		     + ");");

		  }
		  
		  @Override
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		            db.execSQL("DROP TABLE IF EXISTS " + DBItem.TABLE_NAME);
		            onCreate(db);
		  }
     
    }

	// ----------------------------------------------------------------------

	private SQLiteDatabase mDb;

	// ---------------------------- onCreate ------------------------------
	@Override
	public boolean onCreate() {
		Context context = getContext();
		DatabaseHelper helper = new DatabaseHelper(context);
		mDb = helper.getWritableDatabase();
		// Log.d(TAG, "knowledge provider onCreate() >>>>>");
		return (mDb == null) ? false : true;
	}

	// ---------------------------- query ------------------------------
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// Log.d(TAG, "query() >>>>> " + uri);
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		switch (uriMatcher.match(uri)) {
		case DBItem.ITEM_ID:
			qb.setTables(DBItem.TABLE_NAME);
			qb.appendWhere(BaseColumns._ID + "=" + uri.getPathSegments().get(1));
			break;

		case DBItem.ITEMS:
			qb.setTables(DBItem.TABLE_NAME);
			if (TextUtils.isEmpty(sortOrder))
				sortOrder = DBItem.DEFAULT_SORT_ORDER;
			break;

		default:
			throw new IllegalArgumentException("Unknown URI : " + uri);
		}
		// ---register to watch a content URI for changes---
		Cursor c = qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder); // requery
																								// is
																								// deprecated
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	// ---------------------------- getType ------------------------------
	@Override
	public String getType(Uri uri) {
		// Log.d(TAG, "getType() >>>>> " + uri);
		switch (uriMatcher.match(uri)) {
		case DBItem.ITEM_ID:
			return DBItem.CONTENT_ITEM_TYPE;
		case DBItem.ITEMS:
			return DBItem.CONTENT_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI : " + uri);
		}
	}

	// ---------------------------- insert ------------------------------
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// Log.d(TAG, "insert() >>>>> " + uri);
		long rowId = 0L;
		Uri resultUri = null;

		switch (uriMatcher.match(uri)) {
		case DBItem.ITEMS:
			rowId = mDb.insert(DBItem.TABLE_NAME, null, values);
			if (rowId > 0) {
				resultUri = ContentUris.withAppendedId(DBItem.CONTENT_URI, rowId);
			}
			break;

		default:
			throw new IllegalArgumentException("Unknown URI : " + uri);
		}

		if (resultUri != null) {
			getContext().getContentResolver().notifyChange(uri, null);
			return resultUri;
		}
		throw new SQLException("Failed to insert into : " + uri);

	}

	// ---------------------------- delete ------------------------------
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// Log.d(TAG, "delete() >>>>> " + uri);
		int count = 0;

		switch (uriMatcher.match(uri)) {
		case DBItem.ITEM_ID:
			count = mDb.delete(DBItem.TABLE_NAME, BaseColumns._ID + "=" + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
			break;

		case DBItem.ITEMS:
			count = mDb.delete(DBItem.TABLE_NAME, selection, selectionArgs);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI : " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	// ---------------------------- update ------------------------------
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// Log.d(TAG, "update() >>>>> " + uri);
		int count = 0;

		switch (uriMatcher.match(uri)) {
		case DBItem.ITEM_ID:
			count = mDb.update(DBItem.TABLE_NAME, values, BaseColumns._ID + "=" + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
			break;

		case DBItem.ITEMS:
			count = mDb.update(DBItem.TABLE_NAME, values, selection, selectionArgs);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI : " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	/*
	 * if (c.moveToFirst()) { do { } while (c.moveToNext()); }
	 */

	public static class DBItem implements BaseColumns {
		public static final String TITLE = "title";
		public static final String CONTENT = "content";
		public static final String TIME = "time"; 

		//------------------------------------------------------------------------------------------------------------------------------------
		public static final String TABLE_NAME = "item_table";
		public static final Uri CONTENT_URI = Uri.parse("content://" + MyContentProviderSimple.AUTHORITY + "/navi");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.roadrover.navi";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.roadrover.navi";
		public static final int ITEMS = 201;
		public static final int ITEM_ID = 202;
		public static final String DEFAULT_SORT_ORDER = _ID + " DESC"; // ASC
		public static final String[] PROJECTION = { _ID, TITLE, CONTENT, TIME };

		public static Uri insertItem(Context context, ContentValues values) {
			Uri uri = context.getContentResolver().insert(CONTENT_URI, values);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return uri;
		}
		
		public static int updateItem(Context context, long id, ContentValues values) {
			Uri uri = Uri.withAppendedPath(CONTENT_URI, id + "");
//			Uri uri = ContentUris.withAppendedId(CONTENT_URI, id);
			int count = context.getContentResolver().update(uri, values, null, null);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return count;
		}

		public static int deleteItem(Context context, long id) {
			Uri uri = Uri.withAppendedPath(CONTENT_URI, id + "");
			int count = context.getContentResolver().delete(uri, null, null);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return count;
		}

		public static int clearItem(Context context) {
			int count = context.getContentResolver().delete(CONTENT_URI, null, null);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return count;
		}
		
		public static boolean isItemExist(Context context, String title) {
			return getCount(context, TITLE, title) > 0;
		}

		public static int getCount(Context context, String key, String value) {
			Cursor cursor = context.getContentResolver().query(CONTENT_URI, PROJECTION, key + "='" + value + "'", null, null);
			try {
				if (cursor != null)
					return cursor.getCount();
				else
					return 0;
			} finally {
				cursor.close();
			}

		}

	}

	


}