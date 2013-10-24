package com.game.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.game.duel.R;

public class DuelsFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_duels, container, false);
		
//		FragmentA fa = new FragmentA();
//		FragmentB fb = new FragmentB();
//		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//		transaction.replace(R.id.f1_container, fa);
//		transaction.replace(R.id.f2_container, fb);
//		transaction.commit();

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		
	}
	
//	public void replaceFragmentB() {
//		
//		FragmentC fc = new FragmentC();
//		
//		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//		transaction.replace(R.id.f2_container, fc);
//		transaction.commit();
//	}	
}
