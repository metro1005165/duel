package com.game.duel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.game.duel.FragmentDuelTop.OnCheckBoxSelectedListener;

public class DuelActivity extends Activity implements OnCheckBoxSelectedListener {
	
	private List<DuelMessage> duelMessages = new ArrayList<DuelMessage>();
	private DuelMessageAdapter duelMessageAdapter;
	private int currentOrcHpPercent = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duel);
		
		findViewById(R.id.btnStartMsg).setOnClickListener(listTester);
		findViewById(R.id.btnFightMsg).setOnClickListener(listTester);
		findViewById(R.id.btnFinishMsg).setOnClickListener(listTester);
		
		Log.e("DuelActivity", "Inflated R.layout.activity_duel - success");
		
		FragmentDuelTop duelTop = FragmentDuelTop.newInstance();
		if (duelTop != null) {	
			FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, duelTop).commit();
		}
		Log.e("DuelActivity", "Inflated Fragment - success");
		
		duelMessages.add(new DuelMessage(DuelMessageState.START));
		duelMessageAdapter = new DuelMessageAdapter(this, R.layout.duel_message_start, duelMessages);
		ListView lv = (ListView) findViewById(R.id.duelMessageList);
		Log.e("DuelActivity", "Before setting list adapter");
		lv.setAdapter(duelMessageAdapter);
		Log.e("DuelActivity", "After setting list adapter");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.duel, menu);
		return true;
	}

	@Override
	public void playerActions(HashMap<String, Integer> result) {
		String message = "Attack: " + result.get("attack_type") + "\n" 
				+ "Block #1: " + result.get("block_type_1") + "\n"
				+ "Block #2: " + result.get("block_type_2");
		
		Toast.makeText(DuelActivity.this, message, Toast.LENGTH_LONG).show();
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
	
	public void Calculate(View view) {
		if (view.getId() == R.id.btnOrcHit) {

			int percent = (int) ((5 / 64) * 100);
			currentOrcHpPercent = currentOrcHpPercent - percent;

			ProgressBar myprogressbar = (ProgressBar) findViewById(R.id.player1HpBar);

			Resources res = getResources();
			Rect bounds = myprogressbar.getProgressDrawable().getBounds();

			if (percent >= 50) {
				myprogressbar.setProgressDrawable(res
						.getDrawable(R.drawable.greenprogressbar));
			} else {
				myprogressbar.setProgressDrawable(res
						.getDrawable(R.drawable.redprogressbar));
			}
			myprogressbar.getProgressDrawable().setBounds(bounds);
			myprogressbar.setProgress(percent);
		}
	}
}
