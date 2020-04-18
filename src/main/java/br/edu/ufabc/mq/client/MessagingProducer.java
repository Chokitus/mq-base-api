package br.edu.ufabc.mq.client;

import java.util.Map;

import br.edu.ufabc.mq.exception.MessagingException;
import lombok.Data;

@Data
public abstract class MessagingProducer<C, M> implements Startable {
	protected final C client;
	protected final Map<String, Object> properties;

	public M send(final M message) throws MessagingException {
		try {
			return sendImpl(message);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	protected abstract M sendImpl(M message) throws Exception;

}
