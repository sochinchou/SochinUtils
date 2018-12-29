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

public class MyContentProvider extends ContentProvider {
	public static final String TAG = "MyContentProvider";
	
	public static final String AUTHORITY = "com.sochinchou.provider";
	private static final UriMatcher uriMatcher;
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "navi", DBNavi.ITEMS);
		uriMatcher.addURI(AUTHORITY, "navi/#", DBNavi.ITEM_ID);
		uriMatcher.addURI(AUTHORITY, "message", DBMessage.ITEMS);
		uriMatcher.addURI(AUTHORITY, "message/#", DBMessage.ITEM_ID);
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
		   db.execSQL("CREATE TABLE " + DBNavi.TABLE_NAME + " ("
		     + DBNavi._ID + " INTEGER PRIMARY KEY,"
		     + DBNavi.NAVI_ID + " TEXT,"
		     + DBNavi.NAVI_TYPE+ " TEXT,"
		     + DBNavi.NAVI_ADDTIME+ " TEXT,"
		     + DBNavi.NAVI_CITY + " TEXT,"
		     + DBNavi.NAVI_NAME + " TEXT,"
		     + DBNavi.NAVI_CODE + " TEXT,"
		     + DBNavi.NAVI_CODETYPE + " TEXT,"
		     + DBNavi.NAVI_ADDRESS+ " TEXT,"
		     + DBNavi.NAVI_NAVITYPE + " TEXT,"
		     + DBNavi.NAVI_FAVOUR + " TEXT"
		     + ");");
		   
		   db.execSQL("CREATE TABLE " + DBMessage.TABLE_NAME + " ("
		     + DBMessage._ID + " INTEGER PRIMARY KEY,"
		     + DBMessage.MSG_ID + " TEXT,"
		     + DBMessage.MSG_TYPE + " TEXT,"
		     + DBMessage.MSG_TITLE+ " TEXT,"
		     + DBMessage.MSG_CONTENTS + " TEXT,"
		     + DBMessage.MSG_IMAGES + " TEXT,"
		     + DBMessage.MSG_ADDTIME + " TEXT,"
		     + DBMessage.MSG_READ + " TEXT,"
		     + DBMessage.MSG_SERVNUM + " TEXT" 
		     + ");");
		  }
		  
		  @Override
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		            db.execSQL("DROP TABLE IF EXISTS " + DBNavi.TABLE_NAME);
		            db.execSQL("DROP TABLE IF EXISTS " + DBMessage.TABLE_NAME);
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
		case DBNavi.ITEM_ID:
			qb.setTables(DBNavi.TABLE_NAME);
			qb.appendWhere(BaseColumns._ID + "=" + uri.getPathSegments().get(1));
			break;

		case DBNavi.ITEMS:
			qb.setTables(DBNavi.TABLE_NAME);
			if (TextUtils.isEmpty(sortOrder))
				sortOrder = DBNavi.DEFAULT_SORT_ORDER;
			break;

		case DBMessage.ITEM_ID:
			qb.setTables(DBMessage.TABLE_NAME);
			qb.appendWhere(BaseColumns._ID + "=" + uri.getPathSegments().get(1));
			break;

		case DBMessage.ITEMS:
			qb.setTables(DBMessage.TABLE_NAME);
			if (TextUtils.isEmpty(sortOrder))
				sortOrder = DBMessage.DEFAULT_SORT_ORDER;
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
		case DBNavi.ITEM_ID:
			return DBNavi.CONTENT_ITEM_TYPE;
		case DBNavi.ITEMS:
			return DBNavi.CONTENT_TYPE;
		case DBMessage.ITEM_ID:
			return DBMessage.CONTENT_ITEM_TYPE;
		case DBMessage.ITEMS:
			return DBMessage.CONTENT_TYPE;
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
		case DBNavi.ITEMS:
			rowId = mDb.insert(DBNavi.TABLE_NAME, null, values);
			if (rowId > 0) {
				resultUri = ContentUris.withAppendedId(DBNavi.CONTENT_URI, rowId);
			}
			break;

		case DBMessage.ITEMS:
			rowId = mDb.insert(DBMessage.TABLE_NAME, null, values);
			if (rowId > 0) {
				resultUri = ContentUris.withAppendedId(DBMessage.CONTENT_URI, rowId);
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
		case DBNavi.ITEM_ID:
			count = mDb.delete(DBNavi.TABLE_NAME, BaseColumns._ID + "=" + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
			break;

		case DBNavi.ITEMS:
			count = mDb.delete(DBNavi.TABLE_NAME, selection, selectionArgs);
			break;

		case DBMessage.ITEM_ID:
			count = mDb.delete(DBMessage.TABLE_NAME, BaseColumns._ID + "=" + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
			break;

		case DBMessage.ITEMS:
			count = mDb.delete(DBMessage.TABLE_NAME, selection, selectionArgs);
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
		case DBNavi.ITEM_ID:
			count = mDb.update(DBNavi.TABLE_NAME, values, BaseColumns._ID + "=" + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
			break;

		case DBNavi.ITEMS:
			count = mDb.update(DBNavi.TABLE_NAME, values, selection, selectionArgs);
			break;

		case DBMessage.ITEM_ID:
			count = mDb.update(DBMessage.TABLE_NAME, values, BaseColumns._ID + "=" + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
			break;

		case DBMessage.ITEMS:
			count = mDb.update(DBMessage.TABLE_NAME, values, selection, selectionArgs);
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

	public static class DBNavi implements BaseColumns {
		public static final String NAVI_ID = "id";
		public static final String NAVI_TYPE = "type";
		public static final String NAVI_ADDTIME = "addtime";
		public static final String NAVI_CITY = "city";
		public static final String NAVI_NAME = "name";
		public static final String NAVI_CODE = "code";
		public static final String NAVI_CODETYPE = "codetype";
		public static final String NAVI_ADDRESS = "address";
		public static final String NAVI_NAVITYPE = "navitype";
		public static final String NAVI_FAVOUR = "isfavour"; // 1 favoured 0
																// unfavour
		public static final String KEY_MAXID = "maxid";
		public static final String KEY_EQUTYPE = "equtype";

		//------------------------------------------------------------------------------------------------------------------------------------
		public static final String TABLE_NAME = "navi_table";
		public static final Uri CONTENT_URI = Uri.parse("content://" + MyContentProvider.AUTHORITY + "/navi");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.mypackage.navi";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.mypackage.navi";
		public static final int ITEMS = 201;
		public static final int ITEM_ID = 202;
		public static final String DEFAULT_SORT_ORDER = _ID + " DESC"; // ASC
		public static final String[] PROJECTION = { _ID, NAVI_ADDTIME, NAVI_NAME, NAVI_CODE, NAVI_CODETYPE, NAVI_ADDRESS, NAVI_FAVOUR };
		public static final String[] PROJECTION_ADAPTER = { NAVI_NAME, NAVI_ADDRESS, NAVI_ADDTIME, NAVI_CODE, NAVI_FAVOUR };
		public static final String[] PROJECTION_FAVOUR = { _ID, NAVI_FAVOUR };


		public static Uri insertNavi(Context context, ContentValues values) {
			Uri uri = context.getContentResolver().insert(CONTENT_URI, values);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return uri;
		}
		
		public static int updateNavi(Context context, long id, ContentValues values) {
			Uri uri = Uri.withAppendedPath(CONTENT_URI, id + "");
//			Uri uri = ContentUris.withAppendedId(CONTENT_URI, id);
			int count = context.getContentResolver().update(uri, values, null, null);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return count;
		}

		public static int deleteNavi(Context context, long id) {
			Uri uri = Uri.withAppendedPath(CONTENT_URI, id + "");
			int count = context.getContentResolver().delete(uri, null, null);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return count;
		}

		public static int clearNavi(Context context) {
			int count = context.getContentResolver().delete(CONTENT_URI, null, null);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return count;
		}
		
		public static boolean isNaviExist(Context context, String msgId) {
			return getCount(context, NAVI_ID, msgId) > 0;
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

	
	public static class DBMessage implements BaseColumns {
		public static final String MSG_ID = "id";
		public static final String MSG_TYPE = "type";
		public static final String MSG_TITLE = "title";
		public static final String MSG_CONTENTS = "contents";
		public static final String MSG_IMAGES = "images";
		public static final String MSG_ADDTIME = "addtime";
		public static final String MSG_READ = "isread"; // read 1, unread 0
		public static final String MSG_SERVNUM = "servnum";

		public static final String MSG_COUNT_SMCOUNT = "smcount";
		public static final String MSG_COUNT_MAXID = "maxid";
		public static final String KEY_COUNT_MAXID = "maxid";
		public static final String KEY_COUNT_MAXNID = "maxnid";
		public static final String kEY_LIST_MAXID = "maxid";
		public static final String KEY_LIST_MINID = "minid";

		//------------------------------------------------------------------------------------------------------------------------------------
		public static final String TABLE_NAME = "message_table";
		public static final Uri CONTENT_URI = Uri.parse("content://" + MyContentProvider.AUTHORITY + "/message");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.mypackage.message";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.mypackage.message";
		public static final int ITEMS = 101;
		public static final int ITEM_ID = 102;
		public static final String DEFAULT_SORT_ORDER = _ID + " DESC"; // ASC

		public static final String[] PROJECTION = { _ID, MSG_TITLE, MSG_CONTENTS, MSG_IMAGES, MSG_ADDTIME, MSG_READ };
		public static final String[] PROJECTION_ADAPTER = { MSG_TITLE, MSG_CONTENTS, MSG_ADDTIME, MSG_READ };

		
		public static Uri insertMessage(Context context, ContentValues values) {
			Uri uri = context.getContentResolver().insert(CONTENT_URI, values);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return uri;
		}
		
		public static int updateMessage(Context context, long id, ContentValues values) {
			Uri uri = Uri.withAppendedPath(CONTENT_URI, id + "");
			int count = context.getContentResolver().update(uri, values, null, null);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return count;
		}

		public static int deleteMessage(Context context, long id) {
			Uri uri = Uri.withAppendedPath(CONTENT_URI, id + "");
			int count = context.getContentResolver().delete(uri, null, null);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return count;
		}

		public static int clearMessage(Context context) {
			int count = context.getContentResolver().delete(CONTENT_URI, null, null);
			context.getContentResolver().notifyChange(CONTENT_URI, null);
			return count;
		}

		public static boolean isMessageExist(Context context, String msgId) {
			return getCount(context, MSG_ID, msgId) > 0;
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