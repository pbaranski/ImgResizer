import javax.swing.*;
import java.awt.*;

public class Panel {
    String readPath;
    String writePath;
    String resolution;
    int threadsNum;
    private String resolutions[] = {"30x30", "60x60", "120x120", "240x240"};
    private String[] threads = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public void addPanel() {


        JTextField readField = new JTextField(5);
        JTextField writeField = new JTextField(5);
        JComboBox res = new JComboBox(resolutions);
        JComboBox thr = new JComboBox(threads);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(5, 2));
        myPanel.add(new JLabel("Folder do odczytu:"));
        myPanel.add(readField);
        myPanel.add(new JLabel("Folder do zapisu:"));
        myPanel.add(writeField);
        myPanel.add(new JLabel("Wybierz rozdzielczość:"));
        myPanel.add(res);
        myPanel.add(new JLabel("Wybierz ilość wątków:"));
        myPanel.add(thr);


        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Wpisz wymagane dane", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            this.readPath = readField.getText();
            this.writePath = writeField.getText();
            this.resolution = (String) res.getSelectedItem();
            this.threadsNum = Integer.parseInt(String.valueOf(thr.getSelectedItem()));


            System.out.println("read: " + readField.getText());
            System.out.println("write: " + writeField.getText());
            System.out.println("resolution: " + res.getSelectedItem());
            System.out.println("threads: " + thr.getSelectedItem());
        }

    }

}
