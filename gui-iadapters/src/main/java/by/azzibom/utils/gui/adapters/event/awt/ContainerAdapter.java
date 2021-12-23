package by.azzibom.utils.gui.adapters.event.awt;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface ContainerAdapter extends ContainerListener {

    @Override
    default void componentAdded(ContainerEvent e) {
    }

    @Override
    default void componentRemoved(ContainerEvent e) {
    }
}
