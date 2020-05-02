package br.edu.ufabc.mq.factory;

import java.util.Map;

import br.edu.ufabc.mq.client.AbstractConsumer;
import br.edu.ufabc.mq.client.AbstractProducer;
import br.edu.ufabc.mq.exception.MessagingException;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class AbstractClientFactory<R extends AbstractConsumer<?, ?>, S extends AbstractProducer<?, ?>>
		implements AutoCloseable {

	protected final Map<String, Object> clientFactoryProperties;

	public AbstractClientFactory(final Map<String, Object> clientFactoryProperties) {
		this.clientFactoryProperties = clientFactoryProperties;
	}

	public R createConsumer(final Map<String, Object> consumerProperties) throws MessagingException {
		try {
			return createConsumerImpl(consumerProperties);
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	public S createProducer(final Map<String, Object> producerProperties) throws MessagingException {
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
	protected abstract R createConsumerImpl(Map<String, Object> consumerProperties) throws Exception;
	protected abstract S createProducerImpl(Map<String, Object> producerProperties) throws Exception;
}
