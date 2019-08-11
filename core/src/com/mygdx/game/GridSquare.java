package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class GridSquare{
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

    //return values for the vertices due to moving GridSquare to a public class
    public Vector2 returnLLVertex(){
        return lowerLVertex;
    }
    public Vector2 returnULVertex(){
        return upperLVertex;
    }
    public Vector2 returnLRVertex(){
        return lowerRVertex;
    }
    public Vector2 returnURVertex(){
        return upperRVertex;
    }
    public Vector2 returnGridLocation(){
        return locationInGrid;
    }
}
