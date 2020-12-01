package br.edu.ufabc.chokitus.mq.client;

import java.util.Map;

import br.edu.ufabc.chokitus.mq.benchmark.ConfigurationProperties;
import br.edu.ufabc.chokitus.mq.exception.MessagingException;

public interface Startable extends AutoCloseable {

	default void start(final ConfigurationProperties properties) throws MessagingException {
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
	void startImpl(final ConfigurationProperties properties) throws Exception;
}
