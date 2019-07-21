package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//This class handles all loading/unloading of static background elements for the game
public class BackgroundRenderer {

    // private int renderOriginX; // (x, y) coordinates of where we want the image
    // to be loaded at
    // private int renderOriginY;
    // TODO figure out how to make a coordanate system for drawing images

    public BackgroundRenderer(String imageToLoad, /* int xCoord, int yCoord, */ JFrame loadFrame) { // TODO add image
												    // file variable
	// renderOriginX = xCoord; // retrieve the coordantates that we want the image
	// loaded at
	// renderOriginY = yCoord;
	JLabel imageLabel = new JLabel(new ImageIcon(loadImage(imageToLoad)));
	loadFrame.add(imageLabel);
    }

    private BufferedImage loadImage(String filename) { // this loads the image to display
	BufferedImage backgroundImg = null;
	try {
	    backgroundImg = ImageIO.read(new File("assets/" + filename));
	    return backgroundImg;
	} catch (IOException e) {
	    return null;
	}
    }
}