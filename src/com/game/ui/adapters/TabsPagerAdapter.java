package com.game.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.game.ui.fragments.CharDetailsFragment;
import com.game.ui.fragments.DuelFragment;
import com.game.ui.fragments.DuelsFragment;
import com.game.ui.fragments.LocationsFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	
	private static final int FRAGMENT_COUNT = 4;
	private FragmentManager fragmentManager;

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		
		fragmentManager = fm;
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return new CharDetailsFragment();
		case 1:
			return new LocationsFragment();
		case 2:
			return new DuelsFragment();
		case 3:
			return new DuelFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		return FRAGMENT_COUNT;
	}
	
	public Fragment getActiveFragment(ViewPager container, int pos) {
		String name = "android:switcher:" + container.getId() + ":" + pos;
		
		return  fragmentManager.findFragmentByTag(name);
	}

}
