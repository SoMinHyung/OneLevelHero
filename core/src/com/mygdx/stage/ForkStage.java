package com.mygdx.stage;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.ArrowButtonListener;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.location.Fork;
import com.mygdx.model.location.NodeConnection;
import com.mygdx.screen.ForkScreen;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 * 
 */
public class ForkStage extends BaseOverlapStage {
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private PartyManager partymanager;

	@Autowired
	private WorldMapAssets worldMapAssets;

	@Autowired
	private ListenerFactory listenerFactory;
	private Fork forkInfo;

	public Stage makeStage() {
		cameraManager.stretchToDevice(this);

		setButton();

		return this;
	}

	private void makeScene(PositionManager positionManager, NodeAssets nodeAssets) {
		Gdx.app.log("forkStage", String.valueOf(positionManager.getCurrentNodePath()));
		if (nodeAssets.getForkByName(positionManager.getCurrentNodePath()) != null) {
			forkInfo = nodeAssets.getForkByName(positionManager.getCurrentNodePath());
			assetsManager.initScene(forkInfo.getSceneName());
			initSceneLoader(assetsManager.rm);
			sceneLoader.loadScene(forkInfo.getSceneName());
		} else {
			forkInfo = nodeAssets.getForkByName("blackwood");
			assetsManager.initScene(forkInfo.getSceneName());
			initSceneLoader(assetsManager.rm);
			sceneLoader.loadScene(forkInfo.getSceneName());
		}
	}

	private void setButton() {
		Gdx.app.log("forkStage", String.valueOf(positionManager.getCurrentNodePath()));

		makeScene(positionManager, nodeAssets);
		addActor(sceneLoader.getRoot());

		CompositeItem saveButton = sceneLoader.getRoot().getCompositeById("save");
		CompositeItem restButton = sceneLoader.getRoot().getCompositeById("rest");

		saveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ForkScreen.isInSave = true;
			}
		});

		restButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				timeManager.plusMinute(5);
				GameObject gameObject = eventManager.getEventInfo().getGameObjectMap().get("rest_in_fork");
				eventManager.setCurrentGameObject(gameObject);
				ForkScreen.isClickPopup = true;
			}
		});
		String currentNode = positionManager.getCurrentNodePath();
		Map<String, NodeConnection> connectionMap = worldMapAssets.getWorldNodeInfo(currentNode).getNodeConnection();

		for (final Entry<String, NodeConnection> connection : connectionMap.entrySet()) {
			final CompositeItem arrow = sceneLoader.getRoot().getCompositeById(connection.getValue().getArrowName());
			if (arrow != null) {
				arrow.setVisible(true);
				arrow.setTouchable(Touchable.enabled);
				ArrowButtonListener arrowButtonListener = listenerFactory.getArrowButtonListener();
				arrowButtonListener.setConnection(connection);
				arrow.addListener(arrowButtonListener);
			}
		}
	}
}
