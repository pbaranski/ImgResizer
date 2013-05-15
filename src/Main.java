import javax.swing.*;
import java.io.File;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        log("Start");
        String filePath;
        String filePathSave;


        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("x:"));
        myPanel.add(xField);
        myPanel.add(new JLabel("y:"));
        myPanel.add(yField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("x value: " + xField.getText());
            System.out.println("y value: " + yField.getText());
        }






        //filePath = JOptionPane.showInputDialog(null, "Podaj scieżke do pliku");
        filePath = ("D:\\test");
        filePathSave = ("D:\\test2");

        log(filePath);

        HashMap<File, String> filesMap = new HashMap<File, String>();
        File[] files = new File(filePath).listFiles();
        for (File file : files){
            if(file.isFile()){
                filesMap.put(file, "raw");
            }
        }

       log("Czy mapa jest pusta " + filesMap.isEmpty());

       log("Next iterator " + filesMap.keySet().iterator().next());

        try {

        File[] files2 = new File(filePathSave).listFiles();
        for (File f: files2) f.delete();
        }  catch (NullPointerException e){

        }

       // new File("/path/directory").mkdirs();

      //---------------------------------------------------------------------------------------------------------


        final int resizerCount = 5;

        final Gallery gal = new Gallery(filesMap);

        Thread resizers[] = new Thread[resizerCount];
        for (int i = 0; i < resizerCount; i++) resizers[i] = new Thread(new Resizer(gal, i));


        for (Thread t: resizers)t.start();
        for (Thread t: resizers)t.join();


    }


    public static void log(String mes){
        System.out.println(mes);
    }

}
