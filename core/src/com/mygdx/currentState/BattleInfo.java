package com.mygdx.currentState;

import com.mygdx.enums.BattleStateEnum;
import com.mygdx.model.Monster;

public class BattleInfo {
	private Monster monster;
	private BattleStateEnum battleState;

	public BattleStateEnum getBattleState() {
		return battleState;
	}

	public void setBattleState(BattleStateEnum battleState) {
		this.battleState = battleState;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}
}