package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface MouseInputAdapter extends MouseInputListener {

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
}
