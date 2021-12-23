package by.azzibom.utils.gui.adapters.event.awt;

import java.awt.event.InputMethodEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface InputMethodListener extends java.awt.event.InputMethodListener {

    @Override
    default void inputMethodTextChanged(InputMethodEvent event) {
    }

    @Override
    default void caretPositionChanged(InputMethodEvent event) {
    }
}
