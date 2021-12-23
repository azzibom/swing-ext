package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.ListDataEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface ListDataListener extends javax.swing.event.ListDataListener {

    @Override
    default void intervalAdded(ListDataEvent e) {
    }

    @Override
    default void intervalRemoved(ListDataEvent e) {
    }

    @Override
    default void contentsChanged(ListDataEvent e) {
    }
}
