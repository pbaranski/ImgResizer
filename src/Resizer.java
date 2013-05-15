import java.io.File;

public class Resizer implements Runnable {
    int id;
    Gallery gal;
    String writeFile;
    private int IMG_WIDTH = 100;
    private int IMG_HEIGHT = 100;

    public Resizer(Gallery gal, int i, String writeFile, int IMG_WIDTH, int IMG_HEIGHT) {
        this.id = i;
        this.gal = gal;
        this.IMG_WIDTH = IMG_WIDTH;
        this.IMG_HEIGHT = IMG_HEIGHT;
        this.writeFile = writeFile;
    }

    public void run() {
        try {
            File key;
            while ((key = gal.lockPicture()) != null) {
                System.out.println("Processing " + key + " thread " + id);
                ImgProcessor ip = new ImgProcessor();
                ip.processsImage(key, writeFile, IMG_WIDTH, IMG_HEIGHT);
                gal.unlockPicture(key);
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
