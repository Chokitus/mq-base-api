package br.edu.ufabc.chokitus.mq.client;

import java.util.Map;

import br.edu.ufabc.chokitus.mq.benchmark.ConfigurationProperties;
import br.edu.ufabc.chokitus.mq.exception.MessagingException;
import br.edu.ufabc.chokitus.mq.message.AbstractMessage;
import lombok.Data;

@Data
public abstract class AbstractProducer<C, M extends AbstractMessage<?>> implements Startable {
	protected final C client;
	protected final ConfigurationProperties properties;

	@SuppressWarnings("unchecked")
	public M send(final AbstractMessage<?> message) throws MessagingException {
		try {
			return sendImpl((M) message);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	protected abstract M sendImpl(M message) throws Exception;

}
