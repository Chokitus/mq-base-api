package br.edu.ufabc.mq.factory;

import java.util.Map;

import br.edu.ufabc.mq.client.MessagingProducer;
import br.edu.ufabc.mq.client.MessagingReceiver;
import br.edu.ufabc.mq.exception.MessagingException;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class AbstractClientFactory<R extends MessagingReceiver<?, ?>, S extends MessagingProducer<?, ?>> {

	protected final Map<String, Object> clientFactoryProperties;

	public AbstractClientFactory(final Map<String, Object> clientFactoryProperties) {
		this.clientFactoryProperties = clientFactoryProperties;
	}

	public R createReceiver(final Map<String, Object> receiverProperties) throws MessagingException {
		try {
			return createReceiverImpl(receiverProperties);
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

	protected abstract R createReceiverImpl(Map<String, Object> receiverProperties) throws Exception;
	protected abstract S createProducerImpl(Map<String, Object> producerProperties) throws Exception;
}
