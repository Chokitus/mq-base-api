package br.edu.ufabc.mq.factory;

import java.util.Map;

import br.edu.ufabc.mq.client.MessagingProducer;
import br.edu.ufabc.mq.client.MessagingReceiver;
import br.edu.ufabc.mq.client.Startable;
import br.edu.ufabc.mq.exception.MessagingException;
import br.edu.ufabc.mq.message.AbstractMessage;
import lombok.Data;

@Data
public abstract class AbstractWrapperFactory<R extends MessagingReceiver<?, ?>, S extends MessagingProducer<?, ?>, M extends AbstractMessage<?>, A extends AbstractClientFactory<R, S>> {
	protected final A clientFactory;
	protected final Map<String, Object> properties;

	public AbstractWrapperFactory(final Map<String, Object> properties) throws MessagingException {
		this.properties = properties;
		try {
			this.clientFactory = createClientFactory(properties);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	public M createMessage(final String body, final Map<String, Object> messageProperties) throws MessagingException {
		try {
			return createMessageImpl(body, messageProperties, clientFactory);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	public void start(final Startable client, final Map<String, Object> clientStartProperties) throws MessagingException {
		try {
			startImpl(client, clientStartProperties, clientFactory);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	protected abstract A createClientFactory(Map<String, Object> clientFactoryProperties) throws Exception;
	protected abstract void startImpl(Startable client, Map<String, Object> clientStartProperties, A clientFactory) throws Exception;
	protected abstract M createMessageImpl(String body, Map<String, Object> messageProperties, A clientFactory) throws Exception;
}
