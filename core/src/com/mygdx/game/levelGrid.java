package com.mygdx.game;
//this class is to show the program where the squares of the grid are

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

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
        for(int i = 1; i<=ySize; i++) { //iterate for every grid square vertically
            if(yCoord > bgSizeY){
                break;
            }

            int xCoord = 0; //reset the X Coord back to 0 to generate new row

            for (int i2 = 1; i2 <= xSize; i2++) { //iterate for every grid square horizontally

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
                GridSquare tempSquare = new GridSquare(lLXHolder, lLYHolder,lRXHolder,lRYHolder, uLXHolder, uLYHolder,
                        uRXHolder, uRYHolder, i2, i);
                //creates GridSquare to be put into array list
                //creates GridSquare to be put into array list
                gridSquares.add(tempSquare); //adds square to the list
            }

            yCoord += 65; //sets the yCoord to the next level for the next horizontal layer to be generated
        }
    }


    public Vector2 getSquareOrigin(Vector2 squareLocation){ //return the lower left vertext of square whichSquare in the arrayList
        for (int i = 0; i < gridSquares.size(); i++){
            if (squareLocation == gridSquares.get(i).returnGridLocation()){
                return gridSquares.get(i).getBaseCoords();
            }
        }
        return new Vector2(0,0);
    }


    public Vector2 findMouseOnGrid(Vector3 mouseCoords){
        float fMouseX = mouseCoords.x;
        float fMouseY = mouseCoords.y;
        for (int i = 0; i < gridSquares.size(); i++){
            if (fMouseX >= gridSquares.get(i).returnLLVertex().x && fMouseX <= gridSquares.get(i).returnLRVertex().x
            && fMouseY >= gridSquares.get(i).returnLLVertex().y && fMouseY <= gridSquares.get(i).returnURVertex().y){
                return gridSquares.get(i).returnGridLocation();
            }
        }
        return null;
    }

    //these next functions left just in case of future use------------------------------------------------------
    public GridSquare getSquareInGrid(Vector2 whichSquare){
        for (int i = 0; i < gridSquares.size(); i++){
            if (whichSquare.equals(gridSquares.get(i).getSquareLocation())){
                return gridSquares.get(i);
            }
        }
        return null;
    }

    public ArrayList<GridSquare> returnGrid (){
        return gridSquares;
    }

    public Vector2[] getSquareBounds(Vector2 whichSqInGrid){
        //this returns an array of vector2's that tell the bounds for the selected square
        //vertex bounds are given in [0] = lower left, [1] = lower right, [2] = upper left, and [3] = upper right
        for (int i = 0; i < gridSquares.size(); i++){
            if(whichSqInGrid == gridSquares.get(i).getSquareLocation()){
                return gridSquares.get(i).getSquareVertices();
            }
        }
        return null;
    }
    //-----------------------------------------------------------------------------------------------------------
}

