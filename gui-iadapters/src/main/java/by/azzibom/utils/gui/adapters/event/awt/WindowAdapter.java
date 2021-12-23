package by.azzibom.utils.gui.adapters.event.awt;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface WindowAdapter extends WindowListener, WindowStateListener, WindowFocusListener {

    @Override
    default void windowOpened(WindowEvent e) {
    }

    @Override
    default void windowClosing(WindowEvent e) {
    }

    @Override
    default void windowClosed(WindowEvent e) {
    }

    @Override
    default void windowIconified(WindowEvent e) {
    }

    @Override
    default void windowDeiconified(WindowEvent e) {
    }

    @Override
    default void windowActivated(WindowEvent e) {
    }

    @Override
    default void windowDeactivated(WindowEvent e) {
    }

    @Override
    default void windowGainedFocus(WindowEvent e) {
    }

    @Override
    default void windowLostFocus(WindowEvent e) {
    }

    @Override
    default void windowStateChanged(WindowEvent e) {
    }
}
