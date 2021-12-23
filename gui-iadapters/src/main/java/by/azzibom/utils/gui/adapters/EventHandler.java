package by.azzibom.utils.gui.adapters;

/**
 *
 * @author Ihar Misevich
 * @version 1.0
 * */
public interface EventHandler<E> {

    default <Ex extends Throwable, T extends E> void handle(T event) throws Ex {
    }
}
