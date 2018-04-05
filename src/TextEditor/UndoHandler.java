package TextEditor;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import javax.swing.undo.*;

/**
 * Handles logic for undoing typing
 */
public class UndoHandler {

  private static UndoManager undoManager = new UndoManager();

  public static void addUndoCapability(JTextArea pane)
  {
    Document doc = pane.getDocument();
    doc.addUndoableEditListener(new UndoableEditListener() {
      public void undoableEditHappened(UndoableEditEvent evt) {
        UndoHandler.undoManager.addEdit(evt.getEdit());
      }
    });

    pane.getActionMap().put("Undo", new AbstractAction("Undo")
    {
      public void actionPerformed(ActionEvent evt)
      {
        try {
          UndoHandler.undoManager.undo();
        } catch (CannotUndoException e) {
          e.printStackTrace();
        }
      }
    });

    pane.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
    pane.getInputMap().put(KeyStroke.getKeyStroke("meta Z"), "Undo");  // Command key on Mac
  }
}