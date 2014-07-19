package com.drinkgame.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.drinkgame.entidade.Friend;
import com.drinkgame.entidade.Session;
import com.wertonguimaraes.drinkgame.R;

public class FriendsAdapter extends BaseAdapter {

	private List<Friend> mNomesTi;
	private LayoutInflater mInflater;
	
	public FriendsAdapter(Context context, List<Friend> nomesTi) {
		mInflater = LayoutInflater.from(context);
		mNomesTi = nomesTi;
	}

	static class ViewHolder {
		protected TextView nome;
		protected CheckBox checkbox;
	}

	@Override
	public int getCount() {
		return mNomesTi.size();
	}

	@Override
	public Object getItem(int index) {
		return mNomesTi.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int posicao, View view, ViewGroup viewGroup) {
		ViewHolder viewHolder = null;
		if (view == null) {
			view = mInflater.inflate(R.layout.friend_adpter, null);
			viewHolder = new ViewHolder();
			viewHolder.nome = (TextView) view.findViewById(R.id.nomeCompare);
			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
			viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

					int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
					mNomesTi.get(getPosition).setChecked(buttonView.isChecked()); // Set the value of checkbox to maintain its state.

					List<String> lista = Session.getInstancia().getIsCheckedList();

					if(!buttonView.isChecked()){

						int indice = lista.indexOf(mNomesTi.get(getPosition).getName());
						if(indice > -1){
							lista.remove(indice);
						}
					}else{
						boolean existe = false;
						for (String nomes : lista) {
							if(nomes.equals(mNomesTi.get(getPosition).getName())){
								existe = true;
								break;
							}
						}
						if(!existe){
							Session.getInstancia().getIsCheckedList().add(mNomesTi.get(getPosition).getName());
						}
					}
				}
			});

			view.setTag(viewHolder);
			view.setTag(R.id.nomeCompare, viewHolder.nome);
			view.setTag(R.id.check, viewHolder.checkbox);
		}else {
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.checkbox.setTag(posicao); // This line is important.

		String nomeDaTi = mNomesTi.get(posicao).getName();
		viewHolder.nome.setText(nomeDaTi);
		viewHolder.checkbox.setChecked(mNomesTi.get(posicao).isChecked());

		return view;
	}

}