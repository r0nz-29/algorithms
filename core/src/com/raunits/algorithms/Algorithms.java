package com.raunits.algorithms;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.raunits.algorithms.graphs.GraphAlgorithms;

import java.util.List;

public class Algorithms extends ApplicationAdapter {
	Animation animation;
	Stage stage;
	ShapeRenderer shapeRenderer;
	int WIDTH;
	int HEIGHT;

	@Override
	public void create () {
		stage = new Stage();
		shapeRenderer = new ShapeRenderer();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		Gdx.input.setInputProcessor(stage);

		animation = new GraphAlgorithms();
		animation.create(stage, shapeRenderer);

		addAnimationButtons(animation.getButtons());
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

	private void addAnimationButtons(List<TextButton> buttons) {
		for (int i=0; i<buttons.size(); i++) {
			TextButton btn = buttons.get(i);
			btn.setX(WIDTH - btn.getWidth() - 10);
			btn.setY(HEIGHT - btn.getHeight() - 10 - i * 20);
			stage.addActor(btn);
		}
	}
}