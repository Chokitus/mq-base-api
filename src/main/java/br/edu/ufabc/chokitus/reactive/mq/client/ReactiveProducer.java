package br.edu.ufabc.chokitus.reactive.mq.client;

import br.edu.ufabc.chokitus.reactive.mq.AsyncCloseable;
import br.edu.ufabc.chokitus.reactive.mq.message.ReactiveMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveProducer extends AsyncCloseable {
	Mono<Void> produce(final byte[] message, final String destination);
	Mono<Void> produce(final Flux<byte[]> messages, final String destination);
}
