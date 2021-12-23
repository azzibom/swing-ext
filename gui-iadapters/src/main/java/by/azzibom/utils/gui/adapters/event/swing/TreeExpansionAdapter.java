package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface TreeExpansionAdapter extends TreeExpansionListener, TreeWillExpandListener {

    @Override
    default void treeExpanded(TreeExpansionEvent event) {
    }

    @Override
    default void treeCollapsed(TreeExpansionEvent event) {
    }

    @Override
    default void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
    }

    @Override
    default void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
    }
}
