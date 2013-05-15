import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        System.out.println("Start");
        String filePath;
        String filePathSave;
        int threadsCount;
        int imgHeight;
        int imgWidth;
        HashMap<File, String> filesMap;

        Panel panel = new Panel();
        panel.addPanel();

        filePath = panel.readPath;
        filePathSave = panel.writePath + "\\";

        imgHeight = Integer.parseInt(panel.resolution.substring(0, (panel.resolution.indexOf("x"))));
        imgWidth = Integer.parseInt(panel.resolution.substring((panel.resolution.indexOf("x") + 1)));
        threadsCount = panel.threadsNum;

        FileManager fm = new FileManager();

        //TEST
        filePath = ("D:\\test1");
        filePathSave = ("D:\\test2\\");
        fm.clearDirectory(filePathSave);
        //TEST

        filesMap = fm.readFilesInDirectory(filePath);
        final Gallery gal = new Gallery(filesMap);

        Thread resizers[] = new Thread[threadsCount];
        for (int i = 0; i < threadsCount; i++)
            resizers[i] = new Thread(new Resizer(gal, i, filePathSave, imgWidth, imgHeight));

        for (Thread t : resizers) t.start();
        for (Thread t : resizers) t.join();

        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");

        fm.log("    ------------------------------    ");
        fm.log("Test date: " + new Date());
        fm.log("Files to resize: " + fm.fileCounter(filePath));
        fm.log("Files to resize: " + fm.fileCounter(filePathSave));
        fm.log("Size: " + imgHeight + " x " + imgWidth);
        fm.log("Threads: " + threadsCount);
        fm.log("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");

    }


}
