package br.edu.ufabc.mq.exception;

public class MessageQueueException extends Exception {

	private static final long serialVersionUID = 1L;

	public MessageQueueException(final Exception e) {
		super(e);
	}

}
