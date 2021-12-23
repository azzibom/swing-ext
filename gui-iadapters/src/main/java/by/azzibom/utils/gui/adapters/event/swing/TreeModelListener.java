package by.azzibom.utils.gui.adapters.event.swing;

import javax.swing.event.TreeModelEvent;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface TreeModelListener extends javax.swing.event.TreeModelListener {
    @Override
    default void treeNodesChanged(TreeModelEvent e) {
    }

    @Override
    default void treeNodesInserted(TreeModelEvent e) {
    }

    @Override
    default void treeNodesRemoved(TreeModelEvent e) {
    }

    @Override
    default void treeStructureChanged(TreeModelEvent e) {
    }
}
