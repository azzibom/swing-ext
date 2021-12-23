package by.azzi.gui.swing.localizer;

/**
 * интерфейс для загрузки {@link SwingLocalizer}ом
 *
 * @see DefaultBundleStore
 *
 * @author Ihar Misevich
 * @version 1.0
 */
public interface BundleStore {

	/**
	 * метод возвращает список имен бандлов для загрузки
	 * */
	String[] getBundleNames();
}
