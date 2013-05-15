import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;

public class Resizer implements Runnable{
    int id;
    Gallery gal;
    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 100;

    public Resizer(Gallery gal, int i) {
        this.id = i;
        this.gal = gal;
    }

    public void run() {

        try {
              File key;
            while((key=gal.lockPicture())!=null){

            System.out.println("Processing " + key + " thread " + id);

                BufferedImage originalImage = ImageIO.read(key);
                int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                BufferedImage key2 = resizeImage(originalImage, type);
                ImageIO.write(key2, "jpg",new File("D:\\test2\\out_" + key.toString().substring(8,15) + ".jpg"));

            gal.unlockPicture(key);
             Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }
}
