package TextEditor;

import javax.swing.*;
import java.awt.event.*;

/**
 * Handles logic regarding key presses
 */
public class KeyboardHandler extends KeyAdapter {
  private PreferenceHandler prefs;
  private float size;
  
  public KeyboardHandler() {
    prefs = new PreferenceHandler();
    size = prefs.getFontSize();
  }

  /**
   * Change font size
   * @param ex KeyEvent
   */
  public void keyPressed(KeyEvent ex) {
    if (ex.isControlDown() || ex.isMetaDown()) {  // Works on both Windows and Mac
      if (ex.getKeyCode() == KeyEvent.VK_EQUALS) {
        prefs.setFontSize(++size);
      } else if (ex.getKeyCode() == KeyEvent.VK_MINUS) {
        prefs.setFontSize(--size);
      }
    }

    if (ex.getKeyCode() == KeyEvent.VK_ESCAPE) {  // Exit program
      // Ask if user wants to exit
      int confirmExit = JOptionPane.showConfirmDialog(TextEditorFrame.getTextArea(),
              "Are you sure you want to exit?",
              "EXIT", JOptionPane.YES_NO_OPTION);

      if (confirmExit == JOptionPane.YES_OPTION) {
        System.exit(0);
      }
    }
  }
}