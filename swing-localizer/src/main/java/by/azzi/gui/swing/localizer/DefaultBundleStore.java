package by.azzi.gui.swing.localizer;

/**
 * реализация по умолчанию, возвращает единтвенное имя бандра локазизации.
 * это значит, что {@link SwingLocalizer} будет искать все файлы свойст 'swing_<language>_<country>.properties' в корне jar файла.
 * Пользователь может определить файл свойст 'swing_*' для нужной локали (или переопределить для существующей) создав
 * соответствующий файл в корне своего проекта (ВНИМАНИЕ: при переопределении файла свойств необходимо переопределять все его свойства
 * иначе будут использованы глобальные свойства, т к файл переопределяется полность и не происходит его загрузка)
 *
 * @author Ihar Misevich
 * @version 1.0
 */
public class DefaultBundleStore implements BundleStore
{

	@Override
	public String[] getBundleNames() {
		return new String[] { "swing" };
	}
}
