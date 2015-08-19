package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.TextureEnum;

/**
 * 각각의 유닛에 Texture를 할당해서 쓸 수 있도록 하는 로직 StaticAssets을 다루기 때문에 싱글턴 클래스가 아니다. 위키의
 * 그래픽_작업물_명명규칙을 참고하자
 * http://cien.woobi.co.kr/dokuwiki/doku.php?id=%EC%A0%9C%EC%9E%91%ED%99%9C%EB%
 * 8F%99:%EA%B7%B8%EB%9E%98%ED%94%BD_%EC%9E%91%EC%97%85%EB%AC%BC_%EB%AA%85%EB%AA
 * %85%EA%B7%9C%EC%B9%99
 * 
 * @author Velmont
 * 
 */
public class TextureManager {

	public static Texture getBustTexture(String facePath, String faceNumber) {
		return StaticAssets.assetManager
				.get(StaticAssets.textureMap.get(TextureEnum.BUST + "_" + facePath + "_" + faceNumber), Texture.class);
	}

	public static Texture getStatusTexture(String facePath) {
		Texture statusTexture = StaticAssets.assetManager
				.get(StaticAssets.textureMap.get(TextureEnum.STATUS + "_" + facePath), Texture.class);
		if (statusTexture == null) {
			Gdx.app.log("TextureManager", "TextureEnum.STATUS" + "_" + facePath + "is null");
		}
		return statusTexture;
	}

	public static Texture getCharacterBodyTexture(String facePath) {
		return StaticAssets.assetManager.get(StaticAssets.textureMap.get(TextureEnum.NPC + "_" + facePath),
				Texture.class);
	}

	public static Texture getFaceTexture(String facePath) {
		return StaticAssets.assetManager.get(StaticAssets.textureMap.get(TextureEnum.FACE + "_" + facePath),
				Texture.class);
	}

	public static Texture getMonsterBattleTexture(String facePath) {
		return StaticAssets.assetManager.get(StaticAssets.textureMap.get(TextureEnum.MONSTER + "_" + facePath),
				Texture.class);
	}

	public static Texture getBattleTurnBigTexture(String facePath) {
		return StaticAssets.assetManager.get(
				StaticAssets.textureMap.get(TextureEnum.BATTLE_TURN + "_" + facePath + "_" + "big"), Texture.class);
	}

	public static Texture getBattleTurnSmallTexture(String facePath) {
		return StaticAssets.assetManager.get(
				StaticAssets.textureMap.get(TextureEnum.BATTLE_TURN + "_" + facePath + "_" + "small"), Texture.class);
	}

	public static Texture getMonsterBodyTexture(String facePath) {
		return StaticAssets.assetManager.get(StaticAssets.textureMap.get(TextureEnum.MONSTER + "_" + facePath),
				Texture.class);
	}

	public static Texture getItemTexture(String itemPath) {
		return StaticAssets.assetManager.get(StaticAssets.textureMap.get(TextureEnum.ITEM + "_" + "one_hand_sword"),
				Texture.class);
	}

	public static Texture getBackgroundTexture(String nodeType) {
		return StaticAssets.assetManager.get(StaticAssets.textureMap.get(TextureEnum.BACKGROUND + "_" + nodeType),
				Texture.class);
	}

	public static Texture getBackgroundTexture(String facePath, TextureEnum textureEnum) {
		return StaticAssets.assetManager.get(
				StaticAssets.textureMap.get(TextureEnum.BACKGROUND + "_" + facePath + "_" + textureEnum),
				Texture.class);
	}
}
