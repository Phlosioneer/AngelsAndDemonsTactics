package com.mygdx.game;
//this class is to show the program where the squares of the grid are

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class LevelGrid {

    private Texture gridBackground; //store texture that we're calculating the grid for
    private int bgSizeX; //store X axis size of texture
    private int bgSizeY; //store Y axis size of texture
    private ArrayList<GridSquare> gridSquares; //data structure to hold all the GridSquare data

    LevelGrid(Texture backgroundTexture){
        gridBackground = backgroundTexture; //set the texture
        gridSquares = new ArrayList<>();
        bgSizeX = gridBackground.getWidth(); //get the x value of texture
        bgSizeY = gridBackground.getHeight(); //get the y value of texture
        int horizontalGridSize = bgSizeX / 66; //get the number of squares horizontally
        int verticalGridSize = bgSizeY / 66; //get the number of squares vertically
        generateGrid(horizontalGridSize, verticalGridSize); //generates the grid
    }

    private void generateGrid(int xSize, int ySize){ //Method to generate each GridSquare and save to the ArrayList
        int yCoord = 0; //set the initial Y position to 0
        int uLXHolder; int uLYHolder; int uRXHolder; int uRYHolder; //holder ints for the individual coords
        int lLXHolder; int lLYHolder; int lRXHolder; int lRYHolder;
        String gridCoordHolder; //holds coordinate of square in grid
        for(int i = 0; i<ySize; i++) { //iterate for every grid square vertically
            if(yCoord > bgSizeY){
                break;
            }

            int xCoord = 0; //reset the X Coord back to 0 to generate new row

            for (int i2 = 0; i2 < xSize; i2++) { //iterate for every grid square horizontally

                if(xCoord > bgSizeX){
                    break;
                }
                int yOffset = yCoord + 65; //holder for the upper edge of the square
                lLXHolder = xCoord; //set the lower left cordinates to the the current base x and y values
                lLYHolder = yCoord;
                uLXHolder = xCoord; //set the upper left coordinates to the base X value, plus the top of the square Y
                uLYHolder = yOffset;
                xCoord += 65;
                lRXHolder = xCoord; //set the lower right coordinates
                lRYHolder = yCoord;
                uRXHolder = xCoord; //set the upper right values of the square
                uRYHolder = yOffset;
                gridCoordHolder = "TBD"; //TODO set up overall grid coordinates
                GridSquare tempSquare = new GridSquare(lLXHolder, lLYHolder,lRXHolder,lRYHolder, uLXHolder, uLYHolder, uRXHolder, uRYHolder, gridCoordHolder);
                //creates GridSquare to be put into array list
                gridSquares.add(tempSquare); //adds square to the list
            }

            yCoord += 65; //sets the yCoord to the next level for the next horizontal layer to be generated
        }
    }

    private class GridSquare{
        private Vector2 upperLVertex;
        private Vector2 upperRVertex;
        private Vector2 lowerLVertex;
        private Vector2 lowerRVertex;
        private String gridCoordinates;

        GridSquare(int lLX, int lLY, int lRX, int lRY, int uLX, int uLY, int uRX, int uRY, String gridLocation){
            upperLVertex = new Vector2(uLX, uLY);
            upperRVertex = new Vector2(uRX, uRY);
            lowerLVertex = new Vector2(lLX, lLY);
            lowerRVertex = new Vector2(lRX, lRY);
            gridCoordinates = gridLocation;
        }

    }

}

