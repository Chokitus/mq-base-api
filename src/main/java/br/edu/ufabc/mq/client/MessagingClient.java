package br.edu.ufabc.mq.client;

import br.edu.ufabc.mq.exception.MessageQueueException;
import br.edu.ufabc.mq.message.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MessagingClient<T> implements AutoCloseable {

	protected T channel;

	public MessagingClient(final T channel) {
		this.channel = channel;
	}

	protected abstract void sendImpl(Message message) throws Exception;
	protected abstract Message receiveImpl(String from) throws Exception;
	protected abstract void closeImpl() throws Exception;

	@Override
	public void close() throws MessageQueueException {
		try {
			closeImpl();
		} catch (final Exception e) {
			throw new MessageQueueException(e);
		}
	}

	public void send(final Message message) throws MessageQueueException {
		try {
			sendImpl(message);
		} catch (final Exception e) {
			throw new MessageQueueException(e);
		}
	}

	public void receive(final String from) throws MessageQueueException {
		try {
			receiveImpl(from);
		} catch (final Exception e) {
			throw new MessageQueueException(e);
		}
	}

}
