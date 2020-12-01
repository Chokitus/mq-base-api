package br.edu.ufabc.chokitus.mq.exception;

public class MessagingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MessagingException(final Exception e) {
		super(e);
	}

}
