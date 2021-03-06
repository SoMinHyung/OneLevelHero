package com.mygdx.factory;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.screen.BGMScreen;
import com.mygdx.screen.BattleScreen;
import com.mygdx.screen.BonusPointScreen;
import com.mygdx.screen.BuildingScreen;
import com.mygdx.screen.CGScreen;
import com.mygdx.screen.CharacterChangeScreen;
import com.mygdx.screen.ChatEventScreen;
import com.mygdx.screen.ChoiceOptionScreen;
import com.mygdx.screen.CollectionScreen;
import com.mygdx.screen.CreditScreen;
import com.mygdx.screen.DungeonEntranceScreen;
import com.mygdx.screen.DungeonScreen;
import com.mygdx.screen.EncounterScreen;
import com.mygdx.screen.EndingScreen;
import com.mygdx.screen.FieldScreen;
import com.mygdx.screen.ForkScreen;
import com.mygdx.screen.GameClearScreen;
import com.mygdx.screen.GameObjectScreen;
import com.mygdx.screen.GameOverScreen;
import com.mygdx.screen.GreetingScreen;
import com.mygdx.screen.InventoryScreen;
import com.mygdx.screen.LoadingBarScreen;
import com.mygdx.screen.LogScreen;
import com.mygdx.screen.MenuScreen;
import com.mygdx.screen.SaveScreen;
import com.mygdx.screen.StatusScreen;
import com.mygdx.screen.VillageScreen;
import com.mygdx.screen.WorldMapScreen;

public class ScreenFactory {
	@Autowired
	private transient ApplicationContext context;
	private transient Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		Gdx.app.debug("ScreenFactory", "screenFactory.setGame(game)");
		this.game = game;
	}

	private IntMap<Screen> screens;
	private Stack<Screen> screenstack;

	public ScreenFactory() {
		screens = new IntMap<Screen>();
		screenstack = new Stack<Screen>();
	}

	private Screen getScreenInstance(ScreenEnum screenEnum) {
		Gdx.app.log("ScreenFactory", String.valueOf(screenEnum) + "Screen 호출");
		switch (screenEnum) {
		case BATTLE:
			return context.getBean(BattleScreen.class);
		case BGM:
			return context.getBean(BGMScreen.class);
		case BONUS_POINT:
			return context.getBean(BonusPointScreen.class);
		case BUILDING:
			return context.getBean(BuildingScreen.class);
		case CG:
			return context.getBean(CGScreen.class);
		case CHARACTER_CHANGE:
			return context.getBean(CharacterChangeScreen.class);
		case CHAT_EVENT:
			return context.getBean(ChatEventScreen.class);
		case CHOICE_OPTION:
			return context.getBean(ChoiceOptionScreen.class);
		case COLLECTION:
			return context.getBean(CollectionScreen.class);
		case CREDIT:
			return context.getBean(CreditScreen.class);
		case DUNGEON:
			return context.getBean(DungeonScreen.class);
		case DUNGEON_ENTRANCE:
			return context.getBean(DungeonEntranceScreen.class);
		case ENCOUNTER:
			return context.getBean(EncounterScreen.class);
		case ENDING:
			return context.getBean(EndingScreen.class);
		case GAME_CLEAR:
			return context.getBean(GameClearScreen.class);
		case GAME_OBJECT:
			return context.getBean(GameObjectScreen.class);
		case GAME_OVER:
			return context.getBean(GameOverScreen.class);
		case GREETING:
			return context.getBean(GreetingScreen.class);
		case INVENTORY:
			return context.getBean(InventoryScreen.class);
		case LOADING_BAR:
			return context.getBean(LoadingBarScreen.class);
		case LOG:
			return context.getBean(LogScreen.class);
		case MENU:
			return context.getBean(MenuScreen.class);
		case FIELD:
			return context.getBean(FieldScreen.class);
		case FORK:
			return context.getBean(ForkScreen.class);
		case SAVE:
			return context.getBean(SaveScreen.class);
		case STATUS:
			return context.getBean(StatusScreen.class);
		case VILLAGE:
			return context.getBean(VillageScreen.class);
		case WORLD_MAP:
			return context.getBean(WorldMapScreen.class);
		default:
			return context.getBean(VillageScreen.class); // FIXME
		}
	}

	public void show(ScreenEnum screenEnum) {
		Gdx.app.debug("ScreenFactory", "show(" + String.valueOf(screenEnum) + ")");
		if (game == null) {
			Gdx.app.debug("ScreenFactory", "game is null");
			return;
		}
		if (!screens.containsKey(screenEnum.ordinal()))
			screens.put(screenEnum.ordinal(), getScreenInstance(screenEnum));
		game.setScreen(screens.get(screenEnum.ordinal()));
	}

	public void dispose(ScreenEnum screen) {
		if (!screens.containsKey(screen.ordinal()))
			return;
		screens.remove(screen.ordinal()).dispose();
	}

	public void dispose() {
		for (com.badlogic.gdx.Screen screen : screens.values())
			screen.dispose();
		screens.clear();
	}

	public void push(ScreenEnum screenEnum) {
		game.setScreen(screenstack.push(getScreenInstance(screenEnum)));
	}

	public void pop() {
		screenstack.pop().dispose();
		game.setScreen(screenstack.peek());
	}

	public void popAndPush(ScreenEnum screenEnum) {
		screenstack.pop().dispose();
		game.setScreen(screenstack.push(getScreenInstance(screenEnum)));
	}

	public void popAllAndPush(ScreenEnum screenEnum) {
		while (!screenstack.isEmpty())
			screenstack.pop().dispose();
		game.setScreen(screenstack.push(getScreenInstance(screenEnum)));
	}
}
