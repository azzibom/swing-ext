package by.azzibom.utils.gui.adapters.event.awt;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface ComponentAdapter extends ComponentListener {

    @Override
    default void componentResized(ComponentEvent e) {
    }

    @Override
    default void componentMoved(ComponentEvent e) {
    }

    @Override
    default void componentShown(ComponentEvent e) {
    }

    @Override
    default void componentHidden(ComponentEvent e) {
    }
}
