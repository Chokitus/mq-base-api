package br.edu.ufabc.mq.connection;

import java.util.Map;

import br.edu.ufabc.mq.exception.MessageQueueException;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class ConnectionFactoryWrapper<F, C, T> {

	protected final F factory;

	protected final Map<String, Object> properties;

	public ConnectionFactoryWrapper(final Map<String, Object> properties) throws MessageQueueException {
		this.factory = getFactory();
		this.properties = properties;
	}

	public T getNewClient(final C connection) throws MessageQueueException {
		try {
			return getNewClientImpl(connection);
		} catch (final Exception e) {
			throw new MessageQueueException(e);
		}
	}

	public F getFactory() throws MessageQueueException {
		try {
			return getFactoryImpl();
		} catch (final Exception e) {
			throw new MessageQueueException(e);
		}
	}

	public Connection<C> getConnection() throws MessageQueueException {
		try {
			return new Connection<>(getConnectionImpl());
		} catch (final Exception e) {
			throw new MessageQueueException(e);
		}
	}

	protected abstract T getNewClientImpl(C connection) throws Exception;

	protected abstract F getFactoryImpl() throws Exception;

	protected abstract C getConnectionImpl() throws Exception;
}