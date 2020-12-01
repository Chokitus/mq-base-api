package br.edu.ufabc.chokitus.mq.factory;

import br.edu.ufabc.chokitus.mq.benchmark.ConfigurationProperties;
import br.edu.ufabc.chokitus.mq.client.AbstractReceiver;
import br.edu.ufabc.chokitus.mq.client.AbstractProducer;
import br.edu.ufabc.chokitus.mq.exception.MessagingException;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class AbstractClientFactory<R extends AbstractReceiver<?, ?>, S extends AbstractProducer<?, ?>>
		implements AutoCloseable {

	protected final ConfigurationProperties clientFactoryProperties;

	public AbstractClientFactory(final ConfigurationProperties clientFactoryProperties) {
		this.clientFactoryProperties = clientFactoryProperties;
	}

	public R createConsumer(final ConfigurationProperties consumerProperties) throws MessagingException {
		try {
			return createConsumerImpl(consumerProperties);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	public S createProducer(final ConfigurationProperties producerProperties) throws MessagingException {
		try {
			return createProducerImpl(producerProperties);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	@Override
	public void close() throws MessagingException {
		try {
			closeImpl();
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	protected abstract void closeImpl() throws Exception;
	protected abstract R createConsumerImpl(final ConfigurationProperties consumerProperties) throws Exception;
	protected abstract S createProducerImpl(final ConfigurationProperties producerProperties) throws Exception;
}
