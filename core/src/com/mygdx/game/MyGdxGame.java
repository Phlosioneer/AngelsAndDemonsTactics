package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch; //class used to draw 2D images
    public Texture backgroundImg; //create a texture object (stored in VRAM)
	Texture gridImg; //stores the grid in VRAM
	Texture testUnit; //VRAM, you know the drill
	private OrthographicCamera mainCamera; //creates the main camera object
	private Rectangle background;
    LevelGrid backgroundGrid;
	private int mouseX; //ints to grab mouse coordinates
	private int mouseY;
	private Vector3 screenMousePosition; // holds the mouse coordinates in screen coordinates (origin at top right)
	private Vector3 cameraMousePosition; //holds the mouse coordinates in world/camera coordinates (origin at bottom right)
    Vector2 currentUnitCoords = new Vector2(0, 0);
	
	@Override
	public void create () { //runs once on game startup
		mainCamera = new OrthographicCamera();
		mainCamera.setToOrtho(false, 1920, 1080); //sets camera to always show a 1920x1080 view
		//TODO decide on game resolution
		batch = new SpriteBatch(); //instantize the SpriteBatch
		backgroundImg = new Texture("Grass 4x4.png"); //load background texture
		gridImg = new Texture("grid.png"); //load grid texture
		testUnit = new Texture("test_unit.png");
        backgroundGrid = new LevelGrid(backgroundImg);
		background = new Rectangle(); //this will determine the position of the background
		background.x = 0; //set the x/y coords of the background
		background.y = 0;
		}
		

	@Override
	public void render () { //frame update, aka main game loop
	    mouseX = Gdx.input.getX(); //get mouse coordinates in screen space
	    mouseY = Gdx.input.getY();
	    screenMousePosition = new Vector3(mouseX, mouseY, 0 ); //switch mouse coords to camera based coords
	    cameraMousePosition = mainCamera.unproject(screenMousePosition);
	    Vector2 currentMouseGridSquare = backgroundGrid.findMouseOnGrid(cameraMousePosition);
		Gdx.gl.glClearColor(0, 0, 0, 1); //sets the 'clear' color for openGL (red, green, blue, alpha)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//clears the screen, my understanding is it sets it to the 'clear' color
		mainCamera.update();//updates the camera every frame
        if(Gdx.input.isTouched()){
            currentUnitCoords = currentMouseGridSquare;
        }
        //TEMP CODE------------------------------------------------------------------------------------------------

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){ //terminates program when escape is pressed, until a UI exit is coded
            Gdx.app.exit();
        }

        //TEMP CODE ENDS--------------------------------------------------------------------------------------------
		batch.setProjectionMatrix(mainCamera.combined);//tells the batch system to use the camera's coord system
		batch.begin(); //used to please openGL. everything between batch.begin and batch.end will render
		//once the batch.end command is sent
		batch.draw(backgroundImg, background.x, background.y); //draw the background
		batch.draw(gridImg, background.x, background.y); //draw the grid
		batch.draw(testUnit, currentUnitCoords.x, currentUnitCoords.y); //draw the test unit at the mouse location
		batch.end();
	}
	
	@Override
	public void dispose () { //cleanup for shutting down the game
		batch.dispose(); //clean the loaded files from memory to help Java's garbage collection
        backgroundImg.dispose();
        gridImg.dispose();
        testUnit.dispose();
	}
}
