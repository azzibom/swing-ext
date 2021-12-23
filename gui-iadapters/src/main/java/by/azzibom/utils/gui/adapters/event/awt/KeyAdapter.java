package by.azzibom.utils.gui.adapters.event.awt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface KeyAdapter extends KeyListener {

    @Override
    default void keyTyped(KeyEvent e) {
    }

    @Override
    default void keyPressed(KeyEvent e) {
    }

    @Override
    default void keyReleased(KeyEvent e) {
    }
}
