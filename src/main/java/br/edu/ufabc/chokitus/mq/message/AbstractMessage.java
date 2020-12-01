package br.edu.ufabc.chokitus.mq.message;

import br.edu.ufabc.chokitus.mq.benchmark.ConfigurationProperties;
import br.edu.ufabc.chokitus.mq.exception.MessagingException;
import br.edu.ufabc.chokitus.mq.factory.AbstractWrapperFactory;
import lombok.Data;

import java.util.Objects;

/**
 * A Wrapper Message for each type of message queue system. Is created by the method {@link
 * AbstractWrapperFactory#createMessageForProducer}.
 *
 * @param <M>
 *
 * @author victo
 */
@Data
public abstract class AbstractMessage<M> {

	protected final M message;
	protected final String destination;
	protected final ConfigurationProperties properties;

	public byte[] getBody() throws MessagingException {
		try {
			return getBodyImpl();
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	public boolean isEmpty() {
		return Objects.isNull(message);
	}

	protected abstract byte[] getBodyImpl();
}
