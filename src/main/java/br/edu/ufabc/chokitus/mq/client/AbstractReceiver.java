package br.edu.ufabc.chokitus.mq.client;

import br.edu.ufabc.chokitus.mq.benchmark.ConfigurationProperties;
import br.edu.ufabc.chokitus.mq.exception.MessagingException;
import br.edu.ufabc.chokitus.mq.message.AbstractMessage;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

@Data
public abstract class AbstractReceiver<C, M extends AbstractMessage<?>> implements Startable {
	protected final C client;
	protected final ConfigurationProperties properties;

	protected final M empty = instantiateEmptyMessage();

	public M receive(final String property) throws MessagingException {
		try {
			return consumeImpl(property);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	protected abstract M instantiateEmptyMessage();

	protected abstract M consumeImpl(String property) throws Exception;

}
