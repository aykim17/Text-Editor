package TextEditor;

import java.awt.*;
import javax.swing.*;

/**
 * GUI for the text editor
 */

public class TextEditorFrame extends JFrame {
  private static JTextArea textField;
  private JMenu file;
  private JMenuItem open, save;
  private JMenuBar menuBar;

  private PreferenceHandler prefs;
  
  public TextEditorFrame() {
    super("Text Editor");
    setAlwaysOnTop(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(500, 300);
    setLocationRelativeTo(null);
    prefs = new PreferenceHandler();

    handleTextField();
    handleColorScheme();
    handleMenuBar();

    prefs.configureFont();  // Recall font size that user last used
    
    setVisible(true);
  }
  
  public static JTextArea getTextArea() {
    return textField;
  }

  private void handleTextField() {  // Adds text field to frame
    textField = new JTextArea();
    textField.setLineWrap(true);
    textField.setMargin(new Insets(20, 20, 20, 20));
    textField.addKeyListener(new KeyboardHandler());

    UndoHandler.addUndoCapability(textField);
    Container contentPane = getContentPane();
    contentPane.add(new JScrollPane(textField));
  }
  
  private void handleColorScheme() {  // Sets the font and background colors
    textField.setBackground(new Color(240, 234, 210));
    textField.setTabSize(4);
    Color fontColor = new Color(192, 111, 65);
    textField.setForeground(fontColor);
    textField.setCaretColor(fontColor);
  }

  private void handleMenuBar() {  // Puts menu bar at top of frame
    file = new JMenu("File");
    open = new JMenuItem("Open");
    save = new JMenuItem("Save");

    file.add(open);
    file.add(save);
    open.addActionListener(new OpenListener());  // Provide functionality to menu bar
    save.addActionListener(new SaveListener());

    menuBar = new JMenuBar();
    menuBar.add(file);
    setJMenuBar(menuBar);
  }
}