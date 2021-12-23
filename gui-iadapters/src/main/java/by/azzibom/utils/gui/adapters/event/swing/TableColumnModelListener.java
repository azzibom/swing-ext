package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface TableColumnModelListener extends javax.swing.event.TableColumnModelListener {
    @Override
    default void columnAdded(TableColumnModelEvent e) {
    }

    @Override
    default void columnRemoved(TableColumnModelEvent e) {
    }

    @Override
    default void columnMoved(TableColumnModelEvent e) {
    }

    @Override
    default void columnMarginChanged(ChangeEvent e) {
    }

    @Override
    default void columnSelectionChanged(ListSelectionEvent e) {
    }
}
