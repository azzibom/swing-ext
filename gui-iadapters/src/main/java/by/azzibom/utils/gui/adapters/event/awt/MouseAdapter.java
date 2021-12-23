package by.azzibom.utils.gui.adapters.event.awt;

import java.awt.event.*;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface MouseAdapter extends MouseListener, MouseWheelListener, MouseMotionListener {

    @Override
    default void mouseClicked(MouseEvent e) {
    }

    @Override
    default void mousePressed(MouseEvent e) {
    }

    @Override
    default void mouseReleased(MouseEvent e) {
    }

    @Override
    default void mouseEntered(MouseEvent e) {
    }

    @Override
    default void mouseExited(MouseEvent e) {
    }

    @Override
    default void mouseDragged(MouseEvent e) {
    }

    @Override
    default void mouseMoved(MouseEvent e) {
    }

    @Override
    default void mouseWheelMoved(MouseWheelEvent e) {
    }
}
