package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.AncestorEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface AncestorListener extends javax.swing.event.AncestorListener {

    @Override
    default void ancestorAdded(AncestorEvent event) {
    }

    @Override
    default void ancestorRemoved(AncestorEvent event) {
    }

    @Override
    default void ancestorMoved(AncestorEvent event) {
    }
}
