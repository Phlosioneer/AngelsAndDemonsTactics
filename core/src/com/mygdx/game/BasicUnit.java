package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BasicUnit {

    private Texture unitTexture; //texture of the unit
    private Vector2 unitCoords; //current position of the unit in grid coords
    private LevelGrid currentLevelGrid; //grid unit is active on
    private Vector2 renderCoords;


    BasicUnit(String textureForUnit, Vector2 unitStartingCoords, LevelGrid currentGrid){
        unitTexture = new Texture(textureForUnit); //sets texture of unit
        unitCoords = unitStartingCoords; //sets starting location of unit
        currentLevelGrid = currentGrid; //allows the Unit to read the locations on the Grid it is on
        renderCoords = new Vector2(0,0);
    }

    public Vector2 getRenderCoords(){ //returns the true screen coordinates for rendering purposes
        renderCoords = currentLevelGrid.getSquareOrigin(unitCoords);
        return renderCoords;
    }

    public Vector2 currentLocation(){
        return unitCoords;
    }

    public Texture unitTextureReturn(){ //returns the unit's texture for rendering
        return unitTexture;
    }

    public void setUnitCoords (Vector2 newCoords){ //updates the gridSquare that the unit is in
        unitCoords = newCoords;
    } //used for moving the Unit

}