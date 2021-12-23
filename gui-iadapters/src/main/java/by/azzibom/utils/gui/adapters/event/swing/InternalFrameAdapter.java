package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface InternalFrameAdapter extends InternalFrameListener {

    @Override
    default void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    default void internalFrameClosing(InternalFrameEvent e) {
    }

    @Override
    default void internalFrameClosed(InternalFrameEvent e) {
    }

    @Override
    default void internalFrameIconified(InternalFrameEvent e) {
    }

    @Override
    default void internalFrameDeiconified(InternalFrameEvent e) {
    }

    @Override
    default void internalFrameActivated(InternalFrameEvent e) {
    }

    @Override
    default void internalFrameDeactivated(InternalFrameEvent e) {
    }
}
