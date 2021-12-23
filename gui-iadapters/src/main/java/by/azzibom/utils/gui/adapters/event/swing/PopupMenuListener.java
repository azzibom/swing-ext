package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.PopupMenuEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface PopupMenuListener extends javax.swing.event.PopupMenuListener {
    @Override
    default void popupMenuWillBecomeVisible(PopupMenuEvent e) {
    }

    @Override
    default void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
    }

    @Override
    default void popupMenuCanceled(PopupMenuEvent e) {
    }
}
