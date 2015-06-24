package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

/**
 * DungeonEntranceStage와 GameUiStage를 addActor()해서 보여주는 Screen. 던전입구의 경우
 * multiplexer를 이용하여 2개의 화면을 교차로 보여준다.
 * 
 * @author Velmont
 * 
 */
public class DungeonScreen extends RootScreen {
	private Stage dungeonStage;
	private Stage gameUiStage;

	@Override
	public void render(float delta) {
		super.render(delta);

		dungeonStage.draw();
		dungeonStage.getCamera().update();
		gameUiStage.draw();
		// 카메라를 지속적으로 업데이트 해준다.
	}

	@Override
	public void show() {
		dungeonStage = stageFactory.makeStage(StageEnum.DUNGEON);
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);

		// 여러 스테이지에 인풋 프로세서를 동시에 할당한다
		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.

		multiplexer.addProcessor(0, gameUiStage);
		multiplexer.addProcessor(1, dungeonStage);
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		Gdx.input.setInputProcessor(multiplexer);
		musicManager.setWorldNodeMusicAndPlay();
	}

	@Override
	public void hide() {
		gameUiStage.dispose();
		dungeonStage.dispose();
		dispose();
	}
}
