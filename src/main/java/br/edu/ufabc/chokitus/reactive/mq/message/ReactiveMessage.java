package br.edu.ufabc.chokitus.reactive.mq.message;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public abstract class ReactiveMessage<M> {

	private static final Mono<Void> EMPTY = Mono.<Void>empty().cache();

	protected final M message;

	/**
	 * Mono to which, when subscribed, asynchronously ACks message, and return complete signal.
	 * If the message is auto-acked (i.e. Acked before consumption or automatically acked by
	 * the framework, will only play complete signal)
	 */
	protected final Mono<Void> ack;

	public ReactiveMessage(final M message) {
		this(message, EMPTY);
	}

	public Mono<Void> ack() {
		return ack;
	}

	public abstract byte[] getBytes();
}
