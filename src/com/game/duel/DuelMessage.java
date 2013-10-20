package com.game.duel;

public class DuelMessage {

	private DuelMessageState duelMessageState;

	public DuelMessage() {

	}

	public DuelMessage(DuelMessageState duelMessageState) {
		this.duelMessageState = duelMessageState;
	}

	public DuelMessageState getDuelMessageState() {
		return duelMessageState;
	}

	public void setDuelMessageState(DuelMessageState duelMessageState) {
		this.duelMessageState = duelMessageState;
	}
}
