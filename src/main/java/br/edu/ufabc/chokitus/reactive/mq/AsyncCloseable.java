package br.edu.ufabc.chokitus.reactive.mq;

import reactor.core.publisher.Mono;

public interface AsyncCloseable {
	Mono<Void> close();
}
