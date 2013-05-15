import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class FileManager {


    public HashMap<File, String> readFilesInDirectory(String filePath) {
        try {
            HashMap<File, String> filesMap = new HashMap<File, String>();
            File[] files = new File(filePath).listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    filesMap.put(file, "raw");
                }
            }
            return filesMap;
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Plik odczytu niepoprawny", "ERROR", JOptionPane.ERROR_MESSAGE);
            //System.exit(0);
        }

        return null;
    }

    public void clearDirectory(String filePath) {

        try {
            File[] files2 = new File(filePath).listFiles();
            for (File f : files2) f.delete();
        } catch (NullPointerException e) {
            System.out.println("Nothing to clear");
        }
    }

    public int fileCounter(String filePath) {
        try {
            File[] files2 = new File(filePath).listFiles();
            return files2.length;
        } catch (NullPointerException e) {
            System.out.println("Nothing found in directory");
            return 0;
        }
    }

    public void log(String message) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\test1\\log.txt", true)));
            out.println(message);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Niezapisano do logu");
        } catch (IOException e) {
            System.out.println("Niezapisano do logu");
        }

    }


}
