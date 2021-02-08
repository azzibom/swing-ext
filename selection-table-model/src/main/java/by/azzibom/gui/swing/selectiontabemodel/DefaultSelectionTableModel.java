package by.azzibom.gui.swing.selectiontabemodel;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ihar Misevich
 * @version 1.0
 * */
public class DefaultSelectionTableModel extends AbstractSelectionTableModel implements SelectionTableModel {

    public static final int DEFAULT_SELECTION_COL_INDEX = 0;

    private final int selectionColIndex;
    private final String selectionColName;

    private final Set<Integer> selectedRows = new HashSet<>();
    private final TableModel tableModel;
    private RowSelectableStrategy rowSelectableStrategy;

    public DefaultSelectionTableModel(TableModel tableModel) {
        this(tableModel, DEFAULT_SELECTION_COL_INDEX, null, null);
    }

    public DefaultSelectionTableModel(TableModel tableModel, RowSelectableStrategy rss) {
        this(tableModel, DEFAULT_SELECTION_COL_INDEX, null, rss);
    }

    public DefaultSelectionTableModel(TableModel tableModel, int selectionColIndex) {
        this(tableModel, selectionColIndex, null, null);
    }

    public DefaultSelectionTableModel(TableModel tableModel, String selectionColName) {
        this(tableModel, DEFAULT_SELECTION_COL_INDEX, selectionColName, null);
    }

    public DefaultSelectionTableModel(TableModel tableModel, int selectionColIndex, RowSelectableStrategy rss) {
        this(tableModel, selectionColIndex, null, rss);
    }

    public DefaultSelectionTableModel(TableModel tableModel, String selectionColName, RowSelectableStrategy rss) {
        this(tableModel, DEFAULT_SELECTION_COL_INDEX, selectionColName, rss);
    }

    public DefaultSelectionTableModel(TableModel tableModel, int selectionColIndex, String selectionColName, RowSelectableStrategy rss) {
        this.tableModel = tableModel;
        this.selectionColIndex = selectionColIndex;
        this.selectionColName = selectionColName;
        this.rowSelectableStrategy = rss;
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                Integer[] selectedRowsMas = selectedRows.toArray(new Integer[0]);
                if (e.getType() == TableModelEvent.DELETE) {
                    for (int i = 0; i < selectedRowsMas.length; i++) {
                        if (selectedRowsMas[i] >= e.getFirstRow() && selectedRowsMas[i] <= e.getLastRow()) {
                            selectedRowsMas[i] = -1;
                        }
                        if (selectedRowsMas[i] > e.getLastRow()) {
                            selectedRowsMas[i] = selectedRowsMas[i] - ((e.getLastRow() - e.getFirstRow() == 0) ? 1 : e.getLastRow() - e.getFirstRow());
                        }

                    }
                }

                if (e.getType() == TableModelEvent.INSERT) {
                    for (int i = selectedRowsMas.length - 1; i >= 0; i--) {
                        if (selectedRowsMas[i] >= e.getFirstRow()) {
                            selectedRowsMas[i] = selectedRowsMas[i] + ((e.getLastRow() - e.getFirstRow() == 0) ? 1 : e.getLastRow() - e.getFirstRow());
                        }
                    }
                }
                selectedRows.clear();
                selectedRows.addAll(Arrays.asList(selectedRowsMas));
                selectedRows.remove(-1);
                fireTableChanged(new TableModelEvent(DefaultSelectionTableModel.this, e.getFirstRow(), e.getLastRow(), convertIndexFromWrappedTableModelColumn(e.getColumn()), e.getType()));
            }
        });
    }

    @Override
    public int getRowCount() {
        return tableModel.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return tableModel.getColumnCount() + 1;
    }



    protected int convertIndexToWrappedTableModelColumn(int column) {
        return column < selectionColIndex ? column : column - 1;
    }

    protected int convertIndexFromWrappedTableModelColumn(int column) {
        return column < selectionColIndex ? column : column + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == selectionColIndex) {
            return isSelectedRow(rowIndex);
        }
        return tableModel.getValueAt(rowIndex, convertIndexToWrappedTableModelColumn(columnIndex));
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == selectionColIndex) {
            setSelectedRow(Boolean.TRUE.equals(aValue), rowIndex);
        } else {
            tableModel.setValueAt(aValue, rowIndex, convertIndexToWrappedTableModelColumn(columnIndex));
        }
    }

    @Override
    public void setSelectedRow(boolean selected, int row) {
        boolean res;
        if (selected) {
            res = selectedRows.add(row);
        } else {
            res = selectedRows.remove(row);
        }
        if (res) {
            fireTableCellUpdated(row, selectionColIndex);
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column != selectionColIndex) {
            return tableModel.getColumnName(convertIndexToWrappedTableModelColumn(column));
        }
        return selectionColName;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == selectionColIndex) {
            return Boolean.class;
        } else {
            return tableModel.getColumnClass(convertIndexToWrappedTableModelColumn(columnIndex));
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == selectionColIndex) {
            if (rowSelectableStrategy != null) {
                return rowSelectableStrategy.isSelectable(rowIndex);
            }
            return true;
        } else {
            return tableModel.isCellEditable(rowIndex, convertIndexToWrappedTableModelColumn(columnIndex));
        }
    }

    @Override
    public int getSelectedRowCount() {
        return selectedRows.size();
    }

    @Override
    public Set<Integer> getCollectionIndexesSelectedRows() {
        return Collections.unmodifiableSet(selectedRows);
    }

    public int[] getIndexesSelectedRows() {
        int[] selectedRowsArr = new int[selectedRows.size()];
        int i = 0;
        for (Integer selectedRow : selectedRows) {
            selectedRowsArr[i++] = selectedRow;
        }
        return selectedRowsArr;
    }

    /**
     * установить флаг выбора для всех строк
     */
    public void setSelectedAllRows(boolean selectedAllRows) {
        for (int i = 0; i < getRowCount(); i++) {
            if ((selectedAllRows && !isSelectedRow(i))
                    || (!selectedAllRows && isSelectedRow(i))) {
                setSelectedRow(selectedAllRows, i);
            }
        }
    }

    @Override
    public boolean isSelectedRow(int rowIndex) {
        return selectedRows.contains(rowIndex);
    }

    public boolean isSelectedRowsEmpty() {
        return selectedRows.isEmpty();
    }

    public int getSelectionColIndex() {
        return selectionColIndex;
    }

    public String getSelectionColName() {
        return selectionColName;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public DefaultSelectionTableModel setRowSelectableStrategy(RowSelectableStrategy rss) {
        this.rowSelectableStrategy = rss;
        return this;
    }

    public RowSelectableStrategy getRowSelectableStrategy() {
        return rowSelectableStrategy;
    }

    public interface RowSelectableStrategy {

        boolean isSelectable(int row);
    }
}
