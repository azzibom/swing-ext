package by.azzibom.utils.gui.adapters.event.awt;

import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface HierarchyBoundsAdapter extends HierarchyBoundsListener {

    @Override
    default void ancestorMoved(HierarchyEvent e) {
    }

    @Override
    default void ancestorResized(HierarchyEvent e) {
    }
}
