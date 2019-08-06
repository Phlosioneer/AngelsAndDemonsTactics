//simple class to create texture objects a little easier
//TODO find other uses for this class
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
public class TextureLoader {

    private Texture loadedTexture;

    TextureLoader(String levelBackground){
        loadedTexture = new Texture(levelBackground); //sets the background texture to the one called
    }

    public Texture textureReturn(){
        return loadedTexture;
    }

    public void disposeTexture(){
        loadedTexture.dispose();
    }
}