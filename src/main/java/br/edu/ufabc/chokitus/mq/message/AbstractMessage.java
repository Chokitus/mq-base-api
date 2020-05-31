package br.edu.ufabc.chokitus.mq.message;

import java.util.Map;

import br.edu.ufabc.chokitus.mq.exception.MessagingException;
import br.edu.ufabc.chokitus.mq.factory.AbstractWrapperFactory;
import lombok.Data;

/**
 * A Wrapper Message for each type of message queue system. Is created by the
 * methods {@link AbstractWrapperFactory#createMessageForConsumer} and
 * {@link AbstractWrapperFactory#createMessageForProducer}.
 *
 * @author victo
 *
 * @param <M>
 */
@Data
public abstract class AbstractMessage<M> {
	protected final M message;
	protected final String destination;
	protected final Map<String, Object> properties;

	public byte[] getBody() throws MessagingException {
		try {
			return getBodyImpl();
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	protected abstract byte[] getBodyImpl();
}
