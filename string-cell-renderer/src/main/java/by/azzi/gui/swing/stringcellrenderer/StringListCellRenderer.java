package by.azzi.gui.swing.stringcellrenderer;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


/**
 * {@link ListCellRenderer} который работает так же как и {@link DefaultListCellRenderer}
 * за исключением того, что можно задать свой {@link StringConverter <E>} который выведет надпись так как
 * вам надо и учитывает тип объекта
 *
 * @author Ihar Misevich
 */
public class StringListCellRenderer<E> extends StringCellRenderer<E> implements ListCellRenderer<E>, Serializable {

    protected ListCellRenderer<Object> cellRenderer = new DefaultListCellRenderer();

    public StringListCellRenderer(StringConverter<E> stringConverter) {
        super(stringConverter);
    }

    public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected, boolean cellHasFocus) {
        return cellRenderer.getListCellRendererComponent(list, convert(value), index, isSelected, cellHasFocus);
    }
}
