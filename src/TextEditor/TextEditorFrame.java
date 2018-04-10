package TextEditor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * GUI for the text editor
 */

public class TextEditorFrame extends JFrame {

  private static JTextArea textField;
  
  public TextEditorFrame() {
    super("Text Editor");
    setAlwaysOnTop(true);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setSize(500, 300);
    setLocationRelativeTo(null);
    PreferenceHandler prefs = new PreferenceHandler();

    handleWindowClosing();
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
    textField.setCaretPosition(textField.getText().length());  // Stop the cursor from falling behind

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
    JMenu file = new JMenu("File");
    JMenuItem open = new JMenuItem("Open");
    JMenuItem save = new JMenuItem("Save");
    JMenuBar menuBar = new JMenuBar();

    SaveListener saveListener = new SaveListener();
    OpenListener openListener = new OpenListener(saveListener);

    file.add(open);
    file.add(save);
    open.addActionListener(openListener);  // Provide functionality to menu bar
    save.addActionListener(saveListener);

    menuBar.add(file);
    setJMenuBar(menuBar);
  }

  private void handleWindowClosing() {
    addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {
        int confirmExit = JOptionPane.showConfirmDialog(TextEditorFrame.getTextArea(),
                "Are you sure you want to exit?",
                "EXIT", JOptionPane.YES_NO_OPTION);

        if (confirmExit == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }
    });
  }
}