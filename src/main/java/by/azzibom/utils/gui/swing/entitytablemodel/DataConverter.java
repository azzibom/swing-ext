package by.azzibom.utils.gui.swing.entitytablemodel;

/**
 * конвертирует переданный объект в любой другой тип
 *
 * @author Ihar Misevich
 */
public interface DataConverter<E> {

    Object convert(E y);
}
