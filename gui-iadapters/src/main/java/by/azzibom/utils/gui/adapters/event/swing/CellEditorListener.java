package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.ChangeEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface CellEditorListener extends javax.swing.event.CellEditorListener {

    @Override
    default void editingStopped(ChangeEvent e) {
    }

    @Override
    default void editingCanceled(ChangeEvent e) {
    }
}
