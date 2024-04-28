package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Queen;
import model.pieces.Rook;

public class ImageUtil {
	
	private static HashMap<Character, BufferedImage> pieceImages = new HashMap<>();	
	
	public ImageUtil() {
		pieceImages.put('\u2659', addImage(60, 60, "resources/pw.png"));
		pieceImages.put('\u265f', addImage(60, 60, "resources/pb.png"));
		pieceImages.put('\u2656', addImage(60, 60, "resources/rw.png"));
		pieceImages.put('\u265c', addImage(60, 60, "resources/rb.png"));
		pieceImages.put('\u265e', addImage(60, 60, "resources/nb.png"));
		pieceImages.put('\u2658', addImage(60, 60, "resources/nw.png"));
		pieceImages.put('\u265d', addImage(60, 60, "resources/bb.png"));
		pieceImages.put('\u2657', addImage(60, 60, "resources/bw.png"));
		pieceImages.put('\u265b', addImage(60, 60, "resources/qb.png"));
		pieceImages.put('\u2655', addImage(60, 60, "resources/qw.png"));
		pieceImages.put('\u265a', addImage(60, 60, "resources/kb.png"));
		pieceImages.put('\u2654', addImage(60, 60, "resources/kw.png"));
		
	}
	
	public static BufferedImage addImage(int width, int height, String file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(ImageUtil.class.getClassLoader().getResource(file));
		} catch (IOException e) {
			System.out.println("bug here");
			e.printStackTrace();
		}
		image.getScaledInstance(width, height, Image.SCALE_FAST);
		return image;
	}
	
	public static BufferedImage getImage(char character) {
		return pieceImages.get(character);
	}
}
