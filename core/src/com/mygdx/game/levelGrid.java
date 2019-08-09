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
                GridSquare tempSquare = new GridSquare(lLXHolder, lLYHolder,lRXHolder,lRYHolder, uLXHolder, uLYHolder,
                        uRXHolder, uRYHolder, i2, i);
                //creates GridSquare to be put into array list
                //creates GridSquare to be put into array list
                gridSquares.add(tempSquare); //adds square to the list
            }

            yCoord += 65; //sets the yCoord to the next level for the next horizontal layer to be generated
        }
    }

    public int gridSize(){
        return gridSquares.size();
    }

    public Vector2 getSquareOrigin(Vector2 squareLocation){ //return the lower left vertext of square whichSquare in the arrayList
        for (int i = 0; i < gridSquares.size(); i++){
            if (squareLocation == gridSquares.get(i).getSquareLocation()){
                return gridSquares.get(i).getBaseCoords();
            }
        }
        return new Vector2(0,0);
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

    public Vector2 findMouseOnGrid(Vector3 mouseCoords){
        float fMouseX = mouseCoords.x;
        float fMouseY = mouseCoords.y;
        for (int i = 0; i < gridSquares.size(); i++){
            if (fMouseX >= gridSquares.get(i).lowerLVertex.x && fMouseX <= gridSquares.get(i).lowerRVertex.x
            && fMouseY >= gridSquares.get(i).lowerRVertex.y && fMouseY <= gridSquares.get(i).upperRVertex.y){
                return gridSquares.get(i).locationInGrid;
            }
        }
        return null;
    }


    //Line break to seperate classes-----------------------------------------------------------------------------------


    private class GridSquare{
        private Vector2 upperLVertex;
        private Vector2 upperRVertex;
        private Vector2 lowerLVertex;
        private Vector2 lowerRVertex;
        private Vector2 locationInGrid;


        GridSquare(int lLX, int lLY, int lRX, int lRY, int uLX, int uLY, int uRX, int uRY, int gridXLocation,
                   int gridYLocation){
            upperLVertex = new Vector2(uLX, uLY); //these set Vector2's for each of the vertexs of a square in the grid
            upperRVertex = new Vector2(uRX, uRY);
            lowerLVertex = new Vector2(lLX, lLY);
            lowerRVertex = new Vector2(lRX, lRY);
            locationInGrid = new Vector2(gridXLocation, gridYLocation);
        }
        public Vector2 getBaseCoords(){ //return the origin of the square for rendering purposes
            return lowerLVertex;
        }

        public Vector2 getSquareLocation(){ //returns the stored Location in grid as a vector2
            return locationInGrid;
        }

        public Vector2[] getSquareVertices(){ //returns the four vertices of the square for input checking purposes
            Vector2[] verticesToReturn = new Vector2[4];
                verticesToReturn[0] = lowerLVertex;
                verticesToReturn[1] = lowerRVertex;
                verticesToReturn[2] = upperLVertex;
                verticesToReturn[3] = upperRVertex;
                return verticesToReturn;
            }
        }
    }

