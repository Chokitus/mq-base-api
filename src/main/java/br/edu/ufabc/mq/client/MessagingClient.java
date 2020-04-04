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

	public abstract void send(Message message);
	public abstract Message receive(byte[] message);

	@Override
	public void close() throws MessageQueueException {
		try {
			closeImpl();
		} catch (final Exception e) {
			throw new MessageQueueException(e);
		}
	}

	public abstract void closeImpl() throws Exception;
}
