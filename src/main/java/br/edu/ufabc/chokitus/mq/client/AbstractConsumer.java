package br.edu.ufabc.chokitus.mq.client;

import java.util.Map;

import br.edu.ufabc.chokitus.mq.exception.MessagingException;
import br.edu.ufabc.chokitus.mq.message.AbstractMessage;
import lombok.Data;

@Data
public abstract class AbstractConsumer<C, M extends AbstractMessage<?>> implements Startable {
	protected final C client;
	protected final Map<String, Object> properties;

	public M receive(final String property) throws MessagingException {
		try {
			return consumeImpl(property);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	protected abstract M consumeImpl(String property) throws Exception;

}
