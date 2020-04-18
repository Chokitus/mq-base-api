package br.edu.ufabc.mq.client;

import java.util.Map;

import br.edu.ufabc.mq.exception.MessagingException;

public interface Startable extends AutoCloseable {

	default void start(final Map<String, Object> properties) throws MessagingException {
		try {
			startImpl(properties);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	@Override
	default void close() throws Exception {
		try {
			closeImpl();
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	void closeImpl() throws Exception;
	void startImpl(final Map<String, Object> properties) throws Exception;
}
