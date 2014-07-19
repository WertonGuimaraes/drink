package com.drinkgame.database;


import java.util.ArrayList;
import java.util.List;



import com.drinkgame.entidade.Friend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBase {

	public static final String TABLE_FRIENDS = "FRIENDS";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "NAME";
	
	private static final String FRIENDS_CREATE_TABLE = "CREATE TABLE "
			+ TABLE_FRIENDS + "  ("
			+ COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_NAME + " TEXT NOT NULL UNIQUE);";

	private static final String TAG = "DataBaseStorage";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DB_NAME = "DBP";
	private static final int DATABASE_VERSION = 1;

	private final Context mContext;

	public static class DatabaseHelper extends SQLiteOpenHelper {

		@Override
		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);
		}

		DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(FRIENDS_CREATE_TABLE);

			Log.w("DataBaseStorage", "DB created sucefully!");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion,
				int newVersion) {
			Log.w(TAG, "Updating database from version " + oldVersion
					+ " to " + newVersion
					+ ", all data will be lost!");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
			onCreate(db);
		}	    	
	}
	
	public DataBase(Context context) {
		this.mContext = context;
		open();    
	}

	public DataBase open() throws SQLException {
		mDbHelper = new DatabaseHelper(mContext);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDb.close();
	}
	
	public void addFriend(Friend friend) {

		ContentValues values = new ContentValues();

		values.put(COLUMN_NAME, friend.getName());

		mDb.insert(TABLE_FRIENDS, null, values);

	}

	public void removeFriend(Friend friend) {
		mDb.delete(TABLE_FRIENDS, COLUMN_ID + "=?",
				new String[] { String.valueOf(friend.getKey()) });
	}

	public void editFriend(Friend friend, String newName){
		ContentValues values = new ContentValues();

		values.put(COLUMN_NAME, newName);

		mDb.update(TABLE_FRIENDS, values, COLUMN_ID + "=" + friend.getKey(), null);
	}

	public boolean existName(String newName){
		String p_name;
		try
		{
			Cursor mCursor = mDb.query(true, TABLE_FRIENDS, new String[] {COLUMN_NAME},null,null, null, null, null,	null);;

			if(mCursor != null)
			{
				mCursor.moveToFirst();		           
				while(mCursor.isAfterLast() == false){
					p_name = mCursor.getString(mCursor.getColumnIndex(COLUMN_NAME));
					if ((p_name/*.toLowerCase()*/).equals(newName/*.toLowerCase()*/)) {
						return true;
					}
					mCursor.moveToNext();
				}
			}
			mCursor.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}		
		return false;
	}

	public Friend getFriend(int key) {

		Cursor mCursor =

				mDb.query(true, TABLE_FRIENDS, new String[] { COLUMN_ID, COLUMN_NAME}, COLUMN_ID + "=?",
						new String[] { String.valueOf(key) }, null, null, null,
						null);;

						if (mCursor != null) {
							mCursor.moveToFirst();
						}

						String name = mCursor.getString(mCursor.getColumnIndex(COLUMN_NAME));
						Integer id = mCursor.getInt(mCursor.getColumnIndex(COLUMN_ID));

						Friend friendsResult = new Friend(key, name);
						friendsResult.setKey(id);

						return friendsResult;
	}

	public List<Friend> getAllFriends(String filterName) {
		List<Friend> mList = new ArrayList<Friend>();
		Friend mFriend;
		int p_id;
		String p_name;

		Cursor mCursor = mDb.query(true, TABLE_FRIENDS, new String[] { COLUMN_ID, COLUMN_NAME },
				null,null, null, null, null, null);;
		try {
					if(mCursor != null)
					{
						mCursor.moveToFirst();		           
						while(mCursor.isAfterLast() == false)
						{

							p_id = mCursor.getInt(mCursor.getColumnIndex(COLUMN_ID));
							p_name = mCursor.getString(mCursor.getColumnIndex(COLUMN_NAME));

							if (p_name.length() >= filterName.length() && (p_name.substring(0, filterName.length()).toLowerCase()).equals(filterName.toLowerCase())) {
								mFriend = new Friend(p_id,p_name);	
								mList.add(mFriend);
							}


							mCursor.moveToNext();
						}
					}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} finally {
			mCursor.close();
		}
		return mList;
	}

	public DatabaseHelper getMDBHelper(){
		return mDbHelper;
	}

	public SQLiteDatabase getMdb(){
		return mDb;
	}

	public void editFriend(Friend friend) {
		ContentValues values = new ContentValues();

		values.put(COLUMN_NAME, friend.getName());

		mDb.update(TABLE_FRIENDS, values, COLUMN_ID + "=" + friend.getKey(), null);
	}

	public String getPath() {
		return mDb.getPath();
	}
}
