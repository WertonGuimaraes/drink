package com.drinkgame.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.drinkgame.adapter.FriendsAdapter;
import com.drinkgame.database.DataBase;
import com.drinkgame.entidade.Session;
import com.wertonguimaraes.drinkgame.R;

public class CreateGame extends Activity {

	private Context mContext;
	private ListView list;
	private DataBase mDataBase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_game);

		mContext = getApplicationContext();
		
		load_DB();
		displayListView();

	}
	
	private void displayListView() {
		list = (ListView) findViewById(R.id.listNamesFriends);
		FriendsAdapter adapter = new FriendsAdapter(getApplicationContext(), mDataBase.getAllFriends(""));
		list.setAdapter(adapter);		
	}
	
	private void load_DB() {
		mDataBase = new DataBase(mContext);
	}

}