import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgProcessor {

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    public void processsImage(File key, String out, int IMG_WIDTH, int IMG_HEIGHT) {

        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(key);

            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage key2 = resizeImage(originalImage, type, IMG_WIDTH, IMG_HEIGHT);

            String nName = key.getName().toString().substring(0, key.getName().indexOf("."));

            ImageIO.write(key2, "jpg", new File(out + nName + "_" + IMG_HEIGHT + "x" + IMG_WIDTH + ".jpg"));

            System.out.println("Created: " + out + nName + "_" + IMG_HEIGHT + "x" + IMG_WIDTH + ".jpg");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Problem z zapisem", "ERROR", JOptionPane.ERROR_MESSAGE);
            //System.exit(0);
        } catch (NullPointerException e) {
            System.out.println("ERROR niepoprawny plik");
        }

    }
}
