package by.azzibom.utils.gui.adapters.dnd.awt;

import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface DropTargetAdapter extends DropTargetListener {

    @Override
    default void dragEnter(DropTargetDragEvent dtde) {
    }

    @Override
    default void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    default void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    default void dragExit(DropTargetEvent dte) {
    }

    @Override
    default void drop(DropTargetDropEvent dtde) {
    }
}
