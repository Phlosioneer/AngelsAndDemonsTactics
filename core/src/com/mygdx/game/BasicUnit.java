package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BasicUnit {

    private Texture unitTexture; //texture of the unit
    private Vector2 unitCoords; //current position of the unit in grid coords
    private LevelGrid currentLevelGrid; //grid unit is active on
    private Vector2 renderCoords;
    private Vector2 oldUnitCoords;


    BasicUnit(String textureForUnit, Vector2 unitStartingCoords, LevelGrid currentGrid){
        unitTexture = new Texture(textureForUnit); //constructor, initalizes all our variables
        unitCoords = unitStartingCoords;
        currentLevelGrid = currentGrid;
        renderCoords = new Vector2(0,0);
        currentGrid.getSquareInGrid(new Vector2(1,1)).unitIsHere();
    }

    public Vector2 getRenderCoords(){ //returns the true screen coordinates for rendering purposes
        renderCoords = currentLevelGrid.getSquareOrigin(unitCoords);
        return renderCoords;
    }

    public Texture unitTextureReturn(){ //returns the unit's texture for rendering
        return unitTexture;
    }

    public void setUnitCoords (Vector2 newCoords){ //updates the gridSquare that the unit is in
        oldUnitCoords = unitCoords;
        unitCoords = newCoords;
       GridSquare oldSquare = currentLevelGrid.getSquareInGrid(oldUnitCoords); //gets the square that the unit is moving from
       GridSquare currentSquare = currentLevelGrid.getSquareInGrid(unitCoords);
       if (oldSquare != null){ //seems to tell the old square that the unit is not there
       oldSquare.unitHasMoved();
       }
       currentSquare.unitIsHere(); //tells the GridSquare that it contains a unit
    }

}