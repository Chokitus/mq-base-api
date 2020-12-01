package br.edu.ufabc.chokitus.utils;

import java.io.Closeable;
import java.io.IOException;

public interface ResourceUtils {

	/**
	 * Closes resources while sneaking the exception
	 *
	 * @param closeable Resource to be closed
	 */
	static void closeResource(final Closeable closeable) {
		try {
			closeable.close();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
