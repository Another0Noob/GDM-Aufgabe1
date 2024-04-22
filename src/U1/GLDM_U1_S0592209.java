package U1;

import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

//erste Uebung (elementare Bilderzeugung)

public class GLDM_U1_S0592209 implements PlugIn {
	
	final static String[] choices = {
		"Schwarzes Bild",
		"Gelbes Bild", 
		"Belgische Fahne",
		"Schwarz/Weiss Verlauf",
		"Diagonal Weiss/Schwarz Verlauf",
		"Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf",
		"USA Fahne",
		"Tschechische Fahne"
	};
	
	private String choice;
	
	public static void main(String args[]) {
		ImageJ ij = new ImageJ(); // neue ImageJ Instanz starten und anzeigen 
		ij.exitWhenQuitting(true);
		
		GLDM_U1_S0592209 imageGeneration = new GLDM_U1_S0592209();
		imageGeneration.run("");
	}
	
	public void run(String arg) {
		
		int width  = 566;  // Breite
		int height = 400;  // Hoehe
		
		// RGB-Bild erzeugen
		ImagePlus imagePlus = NewImage.createRGBImage("GLDM_U1", width, height, 1, NewImage.FILL_BLACK);
		ImageProcessor ip = imagePlus.getProcessor();
		
		// Arrays fuer den Zugriff auf die Pixelwerte
		int[] pixels = (int[])ip.getPixels();
		
		dialog();
		
		////////////////////////////////////////////////////////////////
		// Hier bitte Ihre Aenderungen / Erweiterungen
		
		if ( choice.equals("Schwarzes Bild") ) {
			generateBlackImage(width, height, pixels);
		}
		if ( choice.equals("Belgische Fahne") ) {
			generateBelgianFlag(width, height, pixels);
		}

		if ( choice.equals("Schwarz/Weiss Verlauf") ) {
			generateHorizontalBWGradient(width, height, pixels);
		}

		if ( choice.equals("Diagonal Weiss/Schwarz Verlauf") ) {
			generateDiagonalBWGradient(width, height, pixels);
		}

		if ( choice.equals("Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf") ) {
			generateBlackRedBlueGradient(width, height, pixels);
		}

		if ( choice.equals("USA Fahne") ) {
			generateUSA(width, height, pixels);
		}

		if ( choice.equals("Tschechische Fahne") ) {
			generateCzechFlag(width, height, pixels);
		}

		////////////////////////////////////////////////////////////////////
		
		// neues Bild anzeigen
		imagePlus.show();
		imagePlus.updateAndDraw();
	}

	private void generateBlackImage(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen
				
				int r = 0;
				int g = 0;
				int b = 0;

				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}
	}

	private void generateBelgianFlag(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen
				int r = 0;
				int g = 0;
				int b = 0;

                if (x > width/3 && x < width * 2/3 ) {
					r = 255;
					g = 255;
					b = 0;
                }

				if (x >= width * 2/3 ) {
					r = 255;
					g = 0;
					b = 0;
				}

                // Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}
	}

	private void generateHorizontalBWGradient(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen

				int r = x*255/width;
				int g = x*255/width;
				int b = x*255/width;

				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}

	}

	private void generateDiagonalBWGradient(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen

				int value = (x + y) * 255 / (width + height);
				int r = 255 - value;
				int g = 255 - value;
				int b = 255 - value;

				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}
	}

	private void generateBlackRedBlueGradient(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen


				int r = x*255/width;
				int g = 0;
				int b = y*255/height;

				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}
	}

	private void generateUSA(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen

				int r = 255;
				int g = 255;
				int b = 255;

				if (y / (height/13) % 2 == 0) {
					r = 178;
					g = 34;
					b = 52;
				}

				if (x <= (2.0/5.0*width) && y <= (7.0/13.0*height)) {
					r = 60;
					g = 59;
					b = 110;
				}


				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}
	}

	private void generateCzechFlag(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen

				int r = 255;
				int g = 255;
				int b = 255;

				if (y > height/2) {
					r = 215;
					g = 20;
					b = 26;
				}

				if (x <= y && x <= height - y) {
					r = 17;
					g = 69;
					b = 126;
				}

				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}
	}


	private void dialog() {
		// Dialog fuer Auswahl der Bilderzeugung
		GenericDialog gd = new GenericDialog("Bildart");
		
		gd.addChoice("Bildtyp", choices, choices[0]);
		
		
		gd.showDialog();	// generiere Eingabefenster
		
		choice = gd.getNextChoice(); // Auswahl uebernehmen
		
		if (gd.wasCanceled())
			System.exit(0);
	}
}

