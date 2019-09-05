package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

//TODO figure out how handling multiple units will work
//TODO begin the process of implementing UI
public class MyGdxGame extends ApplicationAdapter {

	//This section is all fields that have to be initialized in the MyGdxGame.java class-------------------------
	SpriteBatch batch; //class used to draw 2D images
	//these have to be vector3, due to how the coordinate system works
	private Vector3 screenMousePosition; // holds the mouse coordinates in screen coordinates (origin at top right)
	private Vector3 cameraMousePosition; //holds the mouse coordinates in world/camera coordinates (origin at bottom right)
	//-----------------------------------------------------------------------------------------------------------

	//This section is all fields that will eventually be moved to a different class------------------------------
	public Texture backgroundImg; //create a texture object (stored in VRAM)
	Texture gridImg; //stores the grid in VRAM
	BasicUnit testUnit;
	BasicUnit selectedUnit = null;//unit player has selected currently
	private OrthographicCamera mainCamera; //creates the main camera object
	LevelGrid backgroundGrid;
	//-----------------------------------------------------------------------------------------------------------

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
		}
		

	@Override
	public void render () { //frame update, aka main game loop
		handleInput();
		BitmapFont testFont = new BitmapFont(); //for drawing test text

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


        //TEMP CODE ENDS--------------------------------------------------------------------------------------------
		batch.setProjectionMatrix(mainCamera.combined);//tells the batch system to use the camera's coord system
		batch.begin(); //used to please openGL. everything between batch.begin and batch.end will render
		//once the batch.end command is sent
		batch.draw(backgroundImg, 0, 0); //draw the background
		batch.draw(gridImg, 0,0); //draw the grid
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
    private void handleInput() { //function to handle input processing
		//MOUSE PROCESSING
		//gets the grid square in grid coordinates that the mouse is in, easier to pass to other objects then true coords
		Vector2 currentUnitCoords; //vector to start test unit at origin square
		int mouseX = Gdx.input.getX(); //get mouse coordinates in screen space
		int mouseY = Gdx.input.getY();
		screenMousePosition = new Vector3(mouseX, mouseY, 0 ); //switch mouse coords to camera based coords
		cameraMousePosition = mainCamera.unproject(screenMousePosition);
		Vector2 currentMouseGridSquare = backgroundGrid.findMouseOnGrid(cameraMousePosition);
		//END MOUSE PROCESSING

		//TEMP CODE------------------------------------------------------------------------------------------------

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){ //terminates program when escape is pressed, until a UI exit is coded
			Gdx.app.exit();
		}

        if (Gdx.input.isKeyPressed(Input.Keys.A)) { //camera controls taken from libgdx wiki, can edit as needed
            mainCamera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            mainCamera.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            mainCamera.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            mainCamera.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mainCamera.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mainCamera.translate(0, 3, 0);
        }
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && selectedUnit == null && backgroundGrid.findMouseOnGrid(cameraMousePosition).equals(testUnit.currentLocation())){
			//selects the unit clicked on if the square says there is a unit there
			selectedUnit = testUnit;
		}
		else if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && selectedUnit != null){ //update unit position on click, if a unit is selected
			currentUnitCoords = currentMouseGridSquare;
			selectedUnit.setUnitCoords(currentUnitCoords);
			selectedUnit = null; //deselects the unit
		}

        mainCamera.zoom = MathUtils.clamp(mainCamera.zoom, 0.1f, backgroundImg.getHeight()/mainCamera.viewportWidth);

        float effectiveViewportWidth = mainCamera.viewportWidth * mainCamera.zoom;
        float effectiveViewportHeight = mainCamera.viewportHeight * mainCamera.zoom;

		mainCamera.position.x = MathUtils.clamp(mainCamera.position.x, effectiveViewportWidth / 2f, backgroundImg.getWidth() - effectiveViewportWidth / 2f);
		mainCamera.position.y = MathUtils.clamp(mainCamera.position.y, effectiveViewportHeight / 2f, backgroundImg.getHeight() - effectiveViewportHeight / 2f);
    }
}
