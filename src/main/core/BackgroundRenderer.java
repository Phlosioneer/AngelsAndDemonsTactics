package core;

//This class handles all loading/unloading of static background elements for the game
public class BackgroundRenderer {

    private int renderOriginX; // (x, y) coordinates of where we want the image to be loaded at
    private int renderOriginY;

    public BackgroundRenderer(String imageToLoad, int xCoord, int yCoord) { // TODO add image file variable
	renderOriginX = xCoord; // set the class variable to the input varables
	renderOriginY = yCoord;
	loadImage(imageToLoad);
    }

    private void loadImage(String filename) { // TODO create image loader

    }
}