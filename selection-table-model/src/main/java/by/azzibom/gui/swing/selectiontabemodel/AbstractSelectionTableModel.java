package by.azzibom.gui.swing.selectiontabemodel;

import javax.swing.table.AbstractTableModel;

/**
 * @author Ihar Misevich
 * @version 1.0
 * */
public abstract class AbstractSelectionTableModel extends AbstractTableModel implements SelectionTableModel {

    @Override
    public void selectRow(int row) {
        setSelectedRow(true, row);
    }

    @Override
    public void deselectRow(int row) {
        setSelectedRow(false, row);
    }

    @Override
    public void selectAllRows() {
        setSelectedAllRows(true);
    }

    @Override
    public void deselectAllRows() {
        setSelectedAllRows(false);
    }

    public boolean isAllRowsSelected() {
        return getSelectedRowCount() == getRowCount();
    }

    public boolean isSomeRowsSelected() {
        return !isSelectedRowsEmpty();
    }
}
