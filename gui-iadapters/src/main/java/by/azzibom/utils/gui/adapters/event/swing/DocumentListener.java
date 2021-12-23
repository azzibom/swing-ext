package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.DocumentEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface DocumentListener extends javax.swing.event.DocumentListener {

    @Override
    default void insertUpdate(DocumentEvent e) {
    }

    @Override
    default void removeUpdate(DocumentEvent e) {
    }

    @Override
    default void changedUpdate(DocumentEvent e) {
    }
}
