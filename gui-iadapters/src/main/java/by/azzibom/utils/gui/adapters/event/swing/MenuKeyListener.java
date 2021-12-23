package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.MenuKeyEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface MenuKeyListener extends javax.swing.event.MenuKeyListener {
    @Override
    default void menuKeyTyped(MenuKeyEvent e) {
    }

    @Override
    default void menuKeyPressed(MenuKeyEvent e) {
    }

    @Override
    default void menuKeyReleased(MenuKeyEvent e) {
    }
}
