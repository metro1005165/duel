package com.game.ui;

import java.util.HashMap;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.Toast;

import com.game.duel.FragmentDuelTop.OnCheckBoxSelectedListener;
import com.game.duel.R;
import com.game.ui.adapters.TabsPagerAdapter;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener, OnCheckBoxSelectedListener {

	private ViewPager viewPager;
	private TabsPagerAdapter tabsPagerAdapter;
	private ActionBar actionBar;
	
	// Tab titles
	private String[] tabTitles = { 
			"Main", 
			"Locations", 
			"Duels",
			"Test Duel"};
	
	// Tab icons
	private int[] tabIcons = { 
			R.drawable.pirate_smile,
			R.drawable.viking_angry, 
			R.drawable.santa_money,
			R.drawable.skull};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(tabsPagerAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (int i = 0; i < tabTitles.length; i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(tabTitles[i])
					.setTabListener(this)
					.setIcon(tabIcons[i]));	
		}
		
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerActions(HashMap<String, Integer> result) {
		String message = "Attack: " + result.get("attack_type") + "\n" 
				+ "Block #1: " + result.get("block_type_1") + "\n"
				+ "Block #2: " + result.get("block_type_2");
		
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
}
