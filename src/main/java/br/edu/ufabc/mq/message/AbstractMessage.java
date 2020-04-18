package br.edu.ufabc.mq.message;

import br.edu.ufabc.mq.exception.MessagingException;
import lombok.Data;

@Data
public abstract class AbstractMessage<M> {
	protected final M message;

	public String getBody() throws MessagingException {
		try {
			return getBodyImpl();
		} catch (final Exception e) {
			throw new MessagingException(e);
		}
	}

	protected abstract String getBodyImpl();
}
