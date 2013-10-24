package com.game.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.game.duel.DuelMessage;
import com.game.duel.DuelMessageAdapter;
import com.game.duel.DuelMessageState;
import com.game.duel.FragmentDuelTop;
import com.game.duel.HpBar;
import com.game.duel.R;

public class DuelFragment extends Fragment {
	
	private List<DuelMessage> duelMessages = new ArrayList<DuelMessage>();
	private DuelMessageAdapter duelMessageAdapter;
	private HpBar testHpBar;
	private int currentOrcHpPercent = 100;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		currentOrcHpPercent = 100;
		testHpBar.setProgress(currentOrcHpPercent);
		testHpBar.setText("[100/100]");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.activity_duel, container, false);

		view.findViewById(R.id.btnStartMsg).setOnClickListener(listTester);
		view.findViewById(R.id.btnFightMsg).setOnClickListener(listTester);
		view.findViewById(R.id.btnFinishMsg).setOnClickListener(listTester);
		view.findViewById(R.id.btnOrcHit).setOnClickListener(hpTester);
		
		testHpBar = (HpBar) view.findViewById(R.id.player1HpBar);
		
		Log.e("DuelActivity", "Inflated R.layout.activity_duel - success");
		
		FragmentDuelTop duelTop = FragmentDuelTop.newInstance();
		if (duelTop != null) {	
			FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
			transaction.replace(R.id.fragment_container, duelTop).commit();
		}
		Log.e("DuelActivity", "Inflated Fragment - success");
		
		duelMessages.add(new DuelMessage(DuelMessageState.START));
		duelMessageAdapter = new DuelMessageAdapter(getActivity(), R.layout.duel_message_start, duelMessages);
		ListView lv = (ListView) view.findViewById(R.id.duelMessageList);
		Log.e("DuelActivity", "Before setting list adapter");
		lv.setAdapter(duelMessageAdapter);
		Log.e("DuelActivity", "After setting list adapter");

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		
		duelMessages.clear();
		duelMessageAdapter.clear();
		duelMessageAdapter.notifyDataSetChanged();
	}



	private OnClickListener listTester = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			
			case R.id.btnStartMsg:
//				duelMessages.add(new DuelMessage(DuelMessageState.START));
//				duelMessageAdapter.notifyDataSetChanged();
				v.setEnabled(false);
				break;
			case R.id.btnFightMsg:
				duelMessages.add(new DuelMessage(DuelMessageState.FIGHTING));
				duelMessageAdapter.notifyDataSetChanged();
				break;
			case R.id.btnFinishMsg:
				duelMessages.add(new DuelMessage(DuelMessageState.FINISH));
				duelMessageAdapter.notifyDataSetChanged();
				break;
			}	
		}
	};
	
	private OnClickListener hpTester = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {

			case R.id.btnOrcHit:
			
				currentOrcHpPercent = currentOrcHpPercent - 5;

				if (currentOrcHpPercent <= 50) {
					testHpBar.setProgressDrawable(getActivity().getResources()
							.getDrawable(R.drawable.redprogressbar));
				} 
				
				testHpBar.setText("[" + currentOrcHpPercent + "/100]");
				testHpBar.setProgress(currentOrcHpPercent);
		
				break;
			}
		}
	};
}
