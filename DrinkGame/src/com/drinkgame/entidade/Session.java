package com.drinkgame.entidade;

import java.util.ArrayList;
import java.util.List;

public class Session {

	private static Session instancia;
	private List<Friend> atividades;
	private List<String> isCheckedList;

	protected Session() {
		atividades = new ArrayList<Friend>();
		isCheckedList = new ArrayList<String>();
	}

	public static Session getInstancia() {
		if (instancia == null) {
			instancia = new Session();
		}
		return instancia;
	}

	public static void delInstancia(){
		instancia = null;
	}

	public List<String> getIsCheckedList() {
		return isCheckedList;
	}
	
	public List<Friend> getFriends() {
		return atividades;
	}

}