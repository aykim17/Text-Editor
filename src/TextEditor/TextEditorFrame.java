package TextEditor;

import java.awt.*;
import javax.swing.*;

/**
 * GUI for the text editor
 */

public class TextEditorFrame extends JFrame {
  private static JTextArea textField;
  private PreferenceHandler prefs;
  
  public TextEditorFrame() {
    super("Text Editor");
    setAlwaysOnTop(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(500, 300);
    setLocationRelativeTo(null);
    prefs = new PreferenceHandler();
    
    Container c = getContentPane();
    textField = new JTextArea();
    textField.setLineWrap(true);
    textField.setMargin(new java.awt.Insets(20, 20, 20, 20));
    textField.setCaretPosition(textField.getDocument().getLength());
    handleColorScheme();
    prefs.configureFont();
    UndoHandler.addUndoCapability(textField);
    textField.addKeyListener(new KeyboardHandler());
    c.add(new JScrollPane(textField));
    
    setVisible(true);
  }
  
  public static JTextArea getTextArea() {
    return textField;
  }
  
  private void handleColorScheme() {  // Sets the font and background colors
    textField.setBackground(new Color(240, 234, 210));
    textField.setTabSize(4);
    Color fontColor = new Color(192, 111, 65);
    textField.setForeground(fontColor);
    textField.setCaretColor(fontColor);
  }
}