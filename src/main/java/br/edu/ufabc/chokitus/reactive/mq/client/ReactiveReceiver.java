package br.edu.ufabc.chokitus.reactive.mq.client;

import br.edu.ufabc.chokitus.reactive.mq.AsyncCloseable;
import br.edu.ufabc.chokitus.reactive.mq.message.ReactiveMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public interface ReactiveReceiver<M extends ReactiveMessage<?>> extends AsyncCloseable {
	Flux<M> consume(final String queue);
	Flux<M> consumeAutoAck(final String queue);
}
