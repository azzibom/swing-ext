package by.azzibom.utils.gui.adapters.dnd.awt;

import java.awt.dnd.*;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface DragSourceAdapter extends DragSourceListener, DragSourceMotionListener {

    @Override
    default void dragEnter(DragSourceDragEvent dsde) {
    }

    @Override
    default void dragOver(DragSourceDragEvent dsde) {
    }

    @Override
    default void dropActionChanged(DragSourceDragEvent dsde) {
    }

    @Override
    default void dragExit(DragSourceEvent dse) {
    }

    @Override
    default void dragDropEnd(DragSourceDropEvent dsde) {
    }

    @Override
    default void dragMouseMoved(DragSourceDragEvent dsde) {
    }
}
