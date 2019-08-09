package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BasicUnit {

    private Texture unitTexture; //texture of the unit
    private Vector2 unitCoords; //current position of the unit in grid coords
    private LevelGrid currentLevelGrid; //grid unit is active on
    Vector2 renderCoords = new Vector2(0, 0);


    BasicUnit(String textureForUnit, Vector2 unitStartingCoords, LevelGrid currentGrid){
        unitTexture = new Texture(textureForUnit); //constructor, initalizes all our variables
        unitCoords = unitStartingCoords;
        currentLevelGrid = currentGrid;
    }

    public Vector2 getRenderCoords(){ //returns the true screen coordinates for rendering purposes
        return renderCoords;
    }

    public Texture unitTextureReturn(){ //returns the unit's texture for rendering
        return unitTexture;
    }

    public void setUnitCoords (Vector2 newCoords){ //updates the gridSquare that the unit is in
        unitCoords = newCoords;
        Vector2[] squareBounds;
            squareBounds = currentLevelGrid.getSquareBounds(unitCoords);
            renderCoords = squareBounds[0];
    }

}