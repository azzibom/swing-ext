package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.MenuEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface MenuListener extends javax.swing.event.MenuListener {
    @Override
    default void menuSelected(MenuEvent e) {
    }

    @Override
    default void menuDeselected(MenuEvent e) {
    }

    @Override
    default void menuCanceled(MenuEvent e) {
    }
}
