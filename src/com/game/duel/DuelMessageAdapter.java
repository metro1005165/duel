package com.game.duel;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DuelMessageAdapter extends ArrayAdapter<DuelMessage> {
	
	private final List<DuelMessage> duelMessages;
	private final LayoutInflater inflater;

	private static final SparseArray<DuelMessageState> intToMessageStateMap = new SparseArray<DuelMessageState>();
	static {
		for (DuelMessageState type : DuelMessageState.values()) {
			intToMessageStateMap.put(type.ordinal(), type);
		}
	}

	public static class ViewHolder {
		// From R.layout.duel_message_start
		public TextView duelStartTime;
		public TextView orangePlayer;
		public TextView bluePlayer;
		// ***************************
		
		// From R.layout.duel_message_fight
		public TextView fightText;
		// ***************************
		
		// From R.layout.duel_message_finish
		public TextView finishText;
		// ***************************
	}

	public DuelMessageAdapter(Context context, int resource,
			List<DuelMessage> duelMessages) {
		super(context, resource, duelMessages);

		this.duelMessages = duelMessages;
		
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		int layoutType = getItemViewType(position);
		DuelMessageState messageState = intToMessageStateMap.get(layoutType);

		Log.e("getView", "Position: " + position + ", convertView: "
				+ convertView + ", layoutType = " + layoutType);

		if (convertView == null) {
			holder = new ViewHolder();
			switch (messageState) {
			case START:
				convertView = inflater.inflate(R.layout.duel_message_start,
						null);
				holder.duelStartTime = (TextView) convertView
						.findViewById(R.id.tvStartTime);
				holder.orangePlayer = (TextView) convertView
						.findViewById(R.id.tvOrangePlayer);
				holder.bluePlayer = (TextView) convertView
						.findViewById(R.id.tvBluePlayer);
				break;
			case FIGHTING:
				convertView = inflater.inflate(R.layout.duel_message_fight,
						null);
				holder.fightText = (TextView) convertView
						.findViewById(R.id.tvFight);
				break;
			case FINISH:
				convertView = inflater.inflate(R.layout.duel_message_finish,
						null);
				holder.finishText = (TextView) convertView
						.findViewById(R.id.tvFinish);
				break;
			}

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		switch (messageState) {
		case START:
			long dateInMillis = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String dateString = sdf.format(dateInMillis);
			holder.duelStartTime.setText(dateString);
			holder.orangePlayer.setText("Yurka [1]");
			holder.bluePlayer.setText("Artemka [1]");
			break;
		case FIGHTING:
			holder.fightText.setText("Fighting!");
			break;
		case FINISH:
			holder.finishText.setText("Finished!");
			break;
		}

		return convertView;
	}

	@Override
	public int getItemViewType(int position) {

		DuelMessage duelMessage = duelMessages.get(position);
		DuelMessageState messageState = duelMessage.getDuelMessageState();
		
		Log.e("getItemViewType", "Position =  " + position + ", messageState.ordinal() = " + messageState.ordinal());
		return messageState.ordinal();
	}

	@Override
	public int getViewTypeCount() {
		Log.e("getViewTypeCount", " " + DuelMessageState.values().length);
		return DuelMessageState.values().length;
	}	
}
