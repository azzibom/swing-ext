package by.azzibom.gui.swing.selectiontabemodel;

import javax.swing.table.TableModel;
import java.util.Collection;

/**
 * модель таблицы с выбором строк
 *
 * @author Ihar Misevich
 * @version 1.0
 */
public interface SelectionTableModel extends TableModel {

    /**
     * установить флаг выбора строки
     *
     * @param selected флаг
     * @param row индекс строки
     * */
    void setSelectedRow(boolean selected, int row);

    /**
     * узнать выбрана ли определенная строка
     *
     * @param row индекс строки для проверки
     * @return истина, если строка выбрана
     */
    boolean isSelectedRow(int row);

    /**
     * выбрать указанную строку
     * */
    void selectRow(int row);

    /**
     * убрать выбор строки
     * */
    void deselectRow(int row);

    /**
     * установить флаг выбора всех строки
     *
     * @param selected флаг выбора
     * */
    void setSelectedAllRows(boolean selected);

    /**
     * выбрать все строки
     * */
    void selectAllRows();

    /**
     * снять выбор со всех строк
     * */
    void deselectAllRows();

    /**
     * массив индексов выбранных строк
     *
     * @return массив индексов выбранных строк
     */
    int[] getIndexesSelectedRows();

    /**
     * коллекция индексов выбранных строк
     *
     * @return коллекция индексов выбранных строк
     */
    Collection<Integer> getCollectionIndexesSelectedRows();

    /**
     * узнать все ли строки выбраны
     * */
    boolean isAllRowsSelected();

    /**
     * узнать количество выбранных строк
     * */
    int getSelectedRowCount();

    /**
     * узнать что выбранных строк нет
     * */
    boolean isSelectedRowsEmpty();

    /**
     * узнать, что как минимум одна строка выбрана
     * */
    boolean isSomeRowsSelected();
}
