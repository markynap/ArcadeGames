package gameExtras;

import java.applet.Applet;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;


@SuppressWarnings("deprecation")
public class ImageManager extends Applet{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageManager() {

	}
	
	public Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = ImageManager.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch(Exception e) {
			System.out.println("Getting the image failed");
		}
		return tempImage;
	}
	
}