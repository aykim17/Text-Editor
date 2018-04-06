package TextEditor;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * Code for opening file
 */
public class OpenListener implements ActionListener {

  // We need to let the SaveListener know that the file path is already set
  private SaveListener saveListener;

  public OpenListener(SaveListener saveListener) {
    this.saveListener = saveListener;
  }

  public void actionPerformed(ActionEvent event) {
    JFileChooser chooser = new JFileChooser();
    int openOption = chooser.showOpenDialog(TextEditorFrame.getTextArea());
    if (openOption == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      String path = file.getPath();  // Passes file information to the SaveListener
      saveListener.setFilePath(path);
      saveListener.setSaved();
      openFile(file);
    }
  }

  private void openFile(File file) {  // Reads text from input file and writes it to current session
    try {
      FileReader reader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(reader);
      TextEditorFrame.getTextArea().setText("");
      String text;
      while ((text = bufferedReader.readLine()) != null) {
        String currentText = TextEditorFrame.getTextArea().getText();
        TextEditorFrame.getTextArea().setText(currentText + text + "\n");
      }
    } catch (IOException e) {

    }
  }
}