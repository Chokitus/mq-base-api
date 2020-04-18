package br.edu.ufabc.mq.client;

import java.util.Map;

import br.edu.ufabc.mq.exception.MessagingException;
import br.edu.ufabc.mq.message.AbstractMessage;
import lombok.Data;

@Data
public abstract class MessagingReceiver<C, M extends AbstractMessage<?>> implements Startable {
	protected final C client;
	protected final Map<String, Object> properties;

	public M receive(final String property) throws MessagingException {
		try {
			return receiveImpl(property);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	protected abstract M receiveImpl(String property) throws Exception;

}
