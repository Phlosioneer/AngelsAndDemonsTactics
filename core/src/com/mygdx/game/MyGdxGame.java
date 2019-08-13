package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch; //class used to draw 2D images
    public Texture backgroundImg; //create a texture object (stored in VRAM)
	Texture gridImg; //stores the grid in VRAM
	BasicUnit testUnit;
    BasicUnit selectedUnit = null;//unit player has selected currently
	private OrthographicCamera mainCamera; //creates the main camera object
	private Rectangle background;
    LevelGrid backgroundGrid;
	private int mouseX; //ints to grab mouse coordinates
	private int mouseY;
	private Vector3 screenMousePosition; // holds the mouse coordinates in screen coordinates (origin at top right)
	private Vector3 cameraMousePosition; //holds the mouse coordinates in world/camera coordinates (origin at bottom right)
    Vector2 currentUnitCoords = new Vector2(1, 1); //vector to start test unit at origin square

	@Override
	public void create () { //runs once on game startup
		mainCamera = new OrthographicCamera();
		mainCamera.setToOrtho(false, 1920, 1080); //sets camera to always show a 1920x1080 view
		//TODO decide on game resolution
		batch = new SpriteBatch(); //instantize the SpriteBatch
		backgroundImg = new Texture("Grass 4x4.png"); //load background texture
		gridImg = new Texture("grid.png"); //load grid texture

        //MUST BE CALLED BEFORE GENERATING UNITS -------------------------------------------------
        backgroundGrid = new LevelGrid(backgroundImg); //generates the grid for the system.
        //----------------------------------------------------------------------------------------

        testUnit = new BasicUnit("PeaceAngel (1).png", new Vector2(1,1), backgroundGrid);
		background = new Rectangle(); //this will determine the position of the background
		background.x = 0; //set the x/y coords of the background
		background.y = 0;
		}
		

	@Override
	public void render () { //frame update, aka main game loop
		BitmapFont testFont = new BitmapFont(); //for drawing test text

		//MOUSE PROCESSING HAS TO BE DONE HERE
		//gets the grid square in grid coordinates that the mouse is in, easier to pass to other objects then true coords
	    mouseX = Gdx.input.getX(); //get mouse coordinates in screen space
	    mouseY = Gdx.input.getY();
	    screenMousePosition = new Vector3(mouseX, mouseY, 0 ); //switch mouse coords to camera based coords
	    cameraMousePosition = mainCamera.unproject(screenMousePosition);
	    Vector2 currentMouseGridSquare = backgroundGrid.findMouseOnGrid(cameraMousePosition);
	    //END MOUSE PROCESSING

	    //displays mouse coordinates on screen for testing
		String mouseXPos = Float.toString(backgroundGrid.findMouseOnGrid(cameraMousePosition).x);
		String mouseYPos = Float.toString(backgroundGrid.findMouseOnGrid(cameraMousePosition).y);
		CharSequence str = mouseXPos + " , " + mouseYPos;
		// end mouse coord display

		//for testUnit coord display for testing
		String testUnitX = Float.toString(testUnit.currentLocation().x);
		String testUnitY = Float.toString(testUnit.currentLocation().y);
		CharSequence str2 = testUnitX + " , " + testUnitY;
		//end testUnit coord display

		Gdx.gl.glClearColor(0, 0, 0, 1); //sets the 'clear' color for openGL (red, green, blue, alpha)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//clears the screen, my understanding is it sets it to the 'clear' color
		mainCamera.update();//updates the camera every frame
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && selectedUnit == null && backgroundGrid.findMouseOnGrid(cameraMousePosition).equals(testUnit.currentLocation())){
            //selects the unit clicked on if the square says there is a unit there
            selectedUnit = testUnit;
        }
        else if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && selectedUnit != null){ //update unit position on click, if a unit is selected
            currentUnitCoords = currentMouseGridSquare;
            selectedUnit.setUnitCoords(currentUnitCoords);
            selectedUnit = null; //deselects the unit
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
		batch.draw(testUnit.unitTextureReturn(), testUnit.getRenderCoords().x, testUnit.getRenderCoords().y);

		//TEST CODE -----------------------------------------------------------------------------------
		//draw the test unit at the clicked location
		testFont.draw(batch, str, 140, 200);
		testFont.draw(batch, str2, 300, 200);
		if (selectedUnit != null){ //tests to figure out how to get shit to work
			batch.draw(testUnit.unitTextureReturn(), 55, 55);
		}
		if(backgroundGrid.findMouseOnGrid(cameraMousePosition).equals(testUnit.currentLocation())){
			batch.draw(testUnit.unitTextureReturn(), 100, 600);
		}
		//END TEST CODE ---------------------------------------------------------------------------------

		batch.end();
	}
	
	@Override
	public void dispose () { //cleanup for shutting down the game
		batch.dispose(); //clean the loaded files from memory to help Java's garbage collection
        backgroundImg.dispose();
        gridImg.dispose();
        testUnit.unitTextureReturn().dispose();
	}
}
