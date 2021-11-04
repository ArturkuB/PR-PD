package pakiet;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;



public class Main {


	static public void main(String args[]) throws Exception
	{
		
		File input = new File("kwiat.jpg");
		BufferedImage image = ImageIO.read(input);
		int width = image.getWidth();
		int height = image.getHeight();
		Portion a = new Portion(image, 0, width / 2, 0, height / 2);
		
		Portion b = new Portion(image, width / 2, width, 0, height / 2);
		
		Portion c = new Portion(image, 0, width / 2, height / 2, height);
		
		Portion d = new Portion(image, width / 2, width, height / 2, height);
		
		a.negative();
		b.negative();
		c.negative();
		d.negative();
		
		
    	File ouptut = new File("grayscale.jpg");
    	ImageIO.write(image, "jpg", ouptut);
	
	}
}