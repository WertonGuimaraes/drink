package com.drinkgame.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wertonguimaraes.drinkgame.R;

public class MainActivity extends Activity {

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = getApplicationContext();

		ImageView btAddFriend = (ImageView) findViewById(R.id.btAddFriend);
		btAddFriend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(mContext, AddFriend.class);
				startActivity(i);
			}
		});
		
		ImageView btCreateGame = (ImageView) findViewById(R.id.btCreateGame);
		btCreateGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(mContext, CreateGame.class);
				startActivity(i);
			}
		});

	}

}