package TextEditor;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.HashMap;
import java.util.prefs.Preferences;

/**
 * Keeps track of user font size preferences and saves them for next time
 */
public class PreferenceHandler {

  private Preferences prefs;
  private static Font font;
  private InputStream fontFile;
  private float fontSize;
  private String text;

  public PreferenceHandler() {
    prefs = Preferences.userRoot();
    fontFile = getClass().getResourceAsStream("/files/FiraCode-Retina.otf");
    fontSize = prefs.getFloat("fontSize", 24.0F);
    text = prefs.get("text", null);
  }
  
  public float getFontSize() {
    return fontSize;
  }
  
  public void setFontSize(float fontSize) {
    prefs.putFloat("fontSize", fontSize);
    font = font.deriveFont(fontSize);
    TextEditorFrame.getTextArea().setFont(font);
  }
  
  public String getText() {
    return text;
  }
  
  public void configureFont() {
    try {
      HashMap<TextAttribute, Object> map = new HashMap<>();
      map.put(TextAttribute.LIGATURES, TextAttribute.LIGATURES_ON);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      font = Font.createFont(0, fontFile).deriveFont(map).deriveFont(getFontSize());
      ge.registerFont(font);
      TextEditorFrame.getTextArea().setFont(font);
    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }
  }
}
