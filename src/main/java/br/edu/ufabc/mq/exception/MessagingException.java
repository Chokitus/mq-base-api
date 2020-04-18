package br.edu.ufabc.mq.exception;

public class MessagingException extends Exception {

	private static final long serialVersionUID = 1L;

	public MessagingException(final Exception e) {
		super(e);
	}

}
