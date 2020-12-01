package br.edu.ufabc.chokitus.mq.factory;

import br.edu.ufabc.chokitus.mq.benchmark.ConfigurationProperties;
import br.edu.ufabc.chokitus.mq.client.AbstractReceiver;
import br.edu.ufabc.chokitus.mq.client.AbstractProducer;
import br.edu.ufabc.chokitus.mq.exception.MessagingException;
import br.edu.ufabc.chokitus.mq.message.AbstractMessage;
import lombok.Data;

@Data
public abstract class AbstractWrapperFactory<C extends AbstractReceiver<?, ?>, P extends AbstractProducer<?, ?>,
				M extends AbstractMessage<?>, F extends AbstractClientFactory<C, P>> implements AutoCloseable {
	protected final F clientFactory;
	protected final ConfigurationProperties properties;

	/**
	 * This also calls {@link #createClientFactory(ConfigurationProperties)} and utilizes the same property map.
	 *
	 * @param properties Generic map of properties
	 *
	 * @throws MessagingException
	 */
	public AbstractWrapperFactory(final ConfigurationProperties properties) throws MessagingException {
		this.properties = properties;
		try {
			this.clientFactory = createClientFactory(properties);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public M createMessageForProducer(final byte[] body,
	                                  final String destination,
	                                  final AbstractProducer<?, ?> producer,
	                                  final ConfigurationProperties messageProperties) throws MessagingException {
		try {
			return createMessageForProducerImpl(body, destination, (P) producer, messageProperties, clientFactory);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void startConsumer(final AbstractReceiver<?, ?> consumer,
	                          final ConfigurationProperties clientStartProperties) throws MessagingException {
		try {
			startConsumerImpl((C) consumer, clientStartProperties, clientFactory);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void startProducer(final AbstractProducer<?, ?> producer,
	                          final ConfigurationProperties clientStartProperties) throws MessagingException {
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

	protected abstract F createClientFactory(final ConfigurationProperties clientFactoryProperties) throws Exception;

	protected abstract void startConsumerImpl(final C client,
	                                          final ConfigurationProperties clientStartProperties,
	                                          final F clientFactory) throws Exception;

	protected abstract void startProducerImpl(final P client,
	                                          final ConfigurationProperties clientStartProperties,
	                                          final F clientFactory) throws Exception;

	protected abstract M createMessageForProducerImpl(final byte[] body,
	                                                  final String destination,
	                                                  final P producer,
	                                                  final ConfigurationProperties messageProperties,
	                                                  final F clientFactory) throws Exception;

}
