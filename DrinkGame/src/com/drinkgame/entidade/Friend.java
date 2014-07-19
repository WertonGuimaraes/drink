package com.drinkgame.entidade;

public class Friend {
	
	private String mName;
	private int mKey;
	private boolean checked;
	
	public Friend(int key, String name) {
		this.mKey = key;
		this.mName = name;
	}
	
	public Friend(String name) {
		this.mName = name;
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String name) {
		this.mName = name;
	}
	
	public int getKey() {
		return mKey;
	}
	
	public void setKey(int mKey) {
		this.mKey = mKey;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	

}
