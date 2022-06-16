package by.azzi.gui.swing.stringcellrenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.Serializable;

/**
 * Рендер ячейки таблицы по функционалу равный {@link javax.swing.table.DefaultTableCellRenderer}
 * за исключением того что можно указать свой конвертер который преобразует объект в строковое представление так как вам надо
 *
 * @author Ihar Misevih
 */
public class StringTableCellRenderer<E> extends StringCellRenderer<E> implements TableCellRenderer, Serializable {

    protected TableCellRenderer cellRenderer = new DefaultTableCellRenderer();

    public StringTableCellRenderer(StringConverter<E> stringConverter) {
        super(stringConverter);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        return cellRenderer.getTableCellRendererComponent(table, convert(cast(value)), isSelected, hasFocus, row, column);}

    /**
     * метод приведения типа
     * */
    protected E cast(Object value) {
        return (E) value;
    }
}
