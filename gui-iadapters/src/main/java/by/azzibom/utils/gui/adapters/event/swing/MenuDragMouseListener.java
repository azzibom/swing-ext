package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.MenuDragMouseEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface MenuDragMouseListener extends javax.swing.event.MenuDragMouseListener {
    @Override
    default void menuDragMouseEntered(MenuDragMouseEvent e) {
    }

    @Override
    default void menuDragMouseExited(MenuDragMouseEvent e) {
    }

    @Override
    default void menuDragMouseDragged(MenuDragMouseEvent e) {
    }

    @Override
    default void menuDragMouseReleased(MenuDragMouseEvent e) {
    }
}
