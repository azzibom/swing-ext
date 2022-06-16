package by.azzi.gui.swing.stringcellrenderer;

public class StringCellRenderer<E> {

    protected StringConverter<E> stringConverter;

    public StringCellRenderer() {
        this.stringConverter = new StringConverter<E>() {};
    }

    public StringCellRenderer(StringConverter<E> stringConverter) {
        this.stringConverter = stringConverter;
    }

    protected String convert(E value) {
        return (value != null) ? stringConverter.convert(value) : stringConverter.nullConvert();
    }
}
