package br.edu.ufabc.mq.factory;

import java.util.Map;

import br.edu.ufabc.mq.client.AbstractConsumer;
import br.edu.ufabc.mq.client.AbstractProducer;
import br.edu.ufabc.mq.exception.MessagingException;
import br.edu.ufabc.mq.message.AbstractMessage;
import lombok.Data;

@Data
public abstract class AbstractWrapperFactory<C extends AbstractConsumer<?, ?>, P extends AbstractProducer<?, ?>, M extends AbstractMessage<?>, F extends AbstractClientFactory<C, P>>
		implements AutoCloseable {
	protected final F clientFactory;
	protected final Map<String, Object> properties;

	/**
	 * This also calls {@link #createClientFactory(Map)} and utilizes the same
	 * property map.
	 *
	 * @param properties Generic map of properties
	 * @throws MessagingException
	 */
	public AbstractWrapperFactory(final Map<String, Object> properties) throws MessagingException {
		this.properties = properties;
		try {
			this.clientFactory = createClientFactory(properties);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public M createMessageForProducer(final byte[] body, final String destination,
			final AbstractProducer<?, ?> producer, final Map<String, Object> messageProperties)
			throws MessagingException {
		try {
			return createMessageForProducerImpl(body, destination, (P) producer, messageProperties, clientFactory);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	public M createMessageForConsumer(final byte[] body, final String destination, final C consumer,
			final Map<String, Object> messageProperties) throws MessagingException {
		try {
			return createMessageForConsumerImpl(body, destination, consumer, messageProperties, clientFactory);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void startConsumer(final AbstractConsumer<?, ?> consumer, final Map<String, Object> clientStartProperties)
			throws MessagingException {
		try {
			startConsumerImpl((C) consumer, clientStartProperties, clientFactory);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void startProducer(final AbstractProducer<?, ?> producer, final Map<String, Object> clientStartProperties)
			throws MessagingException {
		try {
			startProducerImpl((P) producer, clientStartProperties, clientFactory);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	@Override
	public void close() throws MessagingException {
		try {
			clientFactory.close();
			closeImpl();
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	protected abstract void closeImpl() throws Exception;

	protected abstract F createClientFactory(Map<String, Object> clientFactoryProperties) throws Exception;

	protected abstract void startConsumerImpl(C client, Map<String, Object> clientStartProperties, F clientFactory)
			throws Exception;

	protected abstract void startProducerImpl(P client, Map<String, Object> clientStartProperties, F clientFactory)
			throws Exception;

	protected abstract M createMessageForProducerImpl(byte[] body, String destination, P producer,
			Map<String, Object> messageProperties, F clientFactory) throws Exception;

	protected abstract M createMessageForConsumerImpl(byte[] body, String destination, C consumer,
			Map<String, Object> messageProperties, F clientFactory) throws Exception;

}
