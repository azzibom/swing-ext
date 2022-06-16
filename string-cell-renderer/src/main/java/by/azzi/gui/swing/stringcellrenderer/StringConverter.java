package by.azzi.gui.swing.stringcellrenderer;

/**
 * @author Ihar Misevich
 * */
public interface StringConverter<E> {

    default String convert(E value) {
        return value.toString();
    }

    default String nullConvert() {
        return "";
    }
}
