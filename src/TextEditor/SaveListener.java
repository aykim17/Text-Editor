package TextEditor;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * Code for saving file
 */
public class SaveListener implements ActionListener {

  private boolean isSaved;
  private String fileName;
  private String filePath;

  public SaveListener() {
    isSaved = false;
    fileName = "";
    filePath = "";
  }

  public void setSaved() {  // If we open a file, the save status, filename, and path are already set
    isSaved = true;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public void actionPerformed(ActionEvent event) {
    if (!isSaved) {
      JFileChooser chooser = new JFileChooser();
      int saveOption = chooser.showSaveDialog(TextEditorFrame.getTextArea());
      fileName = chooser.getSelectedFile().getName();
      filePath = chooser.getSelectedFile().getPath();
      // Double check that file name is not empty
      if (saveOption == JFileChooser.APPROVE_OPTION && fileName.trim().length() > 0) {
        saveFile();
        isSaved = true;
      }
    } else {
      saveFile();
    }
  }

  private void saveFile() {  // Writes text to file using fileName
    try {
      String text = TextEditorFrame.getTextArea().getText();
      File file = new File(filePath);
      FileWriter fw = new FileWriter(file);
      fw.write(text);
      fw.close();
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(TextEditorFrame.getTextArea(), "Could not save file!", "Error!", JOptionPane.WARNING_MESSAGE);
      ex.printStackTrace();
    }
  }
}