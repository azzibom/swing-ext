package by.azzi.gui.swing.entitytablemodel;

/**
 * interface of convert object to another type
 *
 * @author Ihar Misevich
 * @version 1.0
 */
public interface DataConverter<E> {

    /**
     * @param y source object to convert
     * @return target converted object
     */
    Object convert(E y);
}
