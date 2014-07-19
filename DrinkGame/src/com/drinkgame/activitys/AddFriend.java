package com.drinkgame.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.drinkgame.database.DataBase;
import com.drinkgame.entidade.Friend;
import com.wertonguimaraes.drinkgame.R;

public class AddFriend extends Activity {

	private Context mContext;
	protected static DataBase mDataBase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_friend);

		mContext = getApplicationContext();
		load_DB();
		
		ImageView btAddFriendInDB = (ImageView) findViewById(R.id.btAddFriendInDB);
		btAddFriendInDB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				EditText etName = (EditText) findViewById(R.id.edName);
				String name = etName.getText().toString();
				
				saveFriendBD(name);
				
				
				
			}
		});
	}

	private void saveFriendBD(String name) {

		Friend friend = new Friend(name);

		if (!mDataBase.existName(friend.getName())) {
			mDataBase.addFriend(friend);
			Toast.makeText(getApplicationContext(), mDataBase.getAllFriends("").size()+"", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(AddFriend.this, MainActivity.class);
			startActivity(intent);
		}
	}
		
	private void load_DB() {
		mDataBase = new DataBase(mContext);
	}

}