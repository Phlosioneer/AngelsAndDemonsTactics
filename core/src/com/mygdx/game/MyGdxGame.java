package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch; //class used to draw 2D images
	Texture backgroundImg; //create a texture object (stored in VRAM)
	private OrthographicCamera mainCamera; //creates the main camera object
	private java.awt.Rectangle background;
	
	@Override
	public void create () { //runs once on game startup
		mainCamera = new OrthographicCamera();
		mainCamera.setToOrtho(false, 1920, 1080); //sets camera to always show a 1920x1080 view
		//TODO decide on game resolution
		batch = new SpriteBatch(); //instantize the SpriteBatch
		backgroundImg = new Texture("Grass 4x4.png"); //load background texture
		background = new Rectangle(); //this will determine the position of the background
		background.x = 0; //set the x/y coords of the background
		background.y = 0;
		}
		

	@Override
	public void render () { //frame update, aka main game loop
		Gdx.gl.glClearColor(0, 0, 0, 1); //sets the 'clear' color for openGL (red, green, blue, alpha)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//clears the screen, my understanding is it sets it to the 'clear' color
		mainCamera.update();//updates the camera every frame
		batch.setProjectionMatrix(backgroundCamera.combined);//tells the batch system to use the camera's coord system
		batch.begin(); //used to please openGL. everything between batch.begin and batch.end will render
		//once the batch.end command is sent
		batch.draw(backgroundImg, background.x, background.y);
		batch.end();
	}
	
	@Override
	public void dispose () { //cleanup for shutting down the game
		batch.dispose(); //clean the loaded files from memory to help Java's garbage collection
		backgroundImg.dispose();
	}
}
