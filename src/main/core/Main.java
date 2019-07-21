package core;

import javax.swing.JFrame;

public class Main {

	public Main() {
		throw new UnsupportedOperationException("Constructor for Main not yet implemented.");
	}

	public static void main(String[] args) {
		var window = new JFrame("Hello World");
		BackgroundRenderer TestBackground = new BackgroundRenderer("Grass 4x4.png", window);// load background image
		window.setSize(500, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
