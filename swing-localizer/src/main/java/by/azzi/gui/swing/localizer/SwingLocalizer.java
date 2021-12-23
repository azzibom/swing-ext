package by.azzi.gui.swing.localizer;

import javax.swing.*;
import java.util.ServiceLoader;

/**
 * класс локализатор swing-а, использует SPI для загрузки пользовательских вариантов локазлизации.
 *
 * @see BundleStore
 * @see DefaultBundleStore
 *
 * @author Ihar Misevich
 * @version 1.0
 */
public final class SwingLocalizer {

	private SwingLocalizer() { }

	public static void localize() {
		ServiceLoader<BundleStore> localizers = ServiceLoader.load(BundleStore.class);
		for (BundleStore bundleStore : localizers) {
			bundleStore.getBundleNames();
			for (String bundleName : bundleStore.getBundleNames()) {
				UIManager.getDefaults().addResourceBundle(bundleName);
			}
		}
	}
}
