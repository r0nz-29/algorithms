package com.raunits.algorithms;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.raunits.algorithms.graphs.GraphAlgorithms;
import com.raunits.algorithms.trees.BinaryTrees;

public class Algorithms extends ApplicationAdapter {
	Animation animation;
	Stage stage;
	ShapeRenderer shapeRenderer;
	int WIDTH;
	int HEIGHT;
	BitmapFont font;
	TextButton refreshButton;
	TextButton.TextButtonStyle textButtonStyle;

	@Override
	public void create () {
		stage = new Stage();
		shapeRenderer = new ShapeRenderer();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		Gdx.input.setInputProcessor(stage);
		addRefreshButton(stage);

		animation = new GraphAlgorithms();
		animation.create(stage, shapeRenderer);
	}

	@Override
	public void render () {
		ScreenUtils.clear(Constants.BACKGROUND);
		stage.draw();

		animation.render(stage, shapeRenderer);
	}
	
	@Override
	public void dispose () {
		animation.dispose();
	}

	private void addRefreshButton(Stage stage) {
		font = new BitmapFont();
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.fontColor = Constants.RED;

		refreshButton = new TextButton("Refresh", textButtonStyle);
		refreshButton.setColor(Constants.RED);

		refreshButton.setX(WIDTH - refreshButton.getWidth() - 10);
		refreshButton.setY(HEIGHT - refreshButton.getHeight());

		stage.addActor(refreshButton);

		refreshButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeListener.ChangeEvent event, Actor actor) {
				animation.onRefresh();
			}
		});
	}
}