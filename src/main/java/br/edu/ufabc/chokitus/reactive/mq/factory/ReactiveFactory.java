package br.edu.ufabc.chokitus.reactive.mq.factory;

import br.edu.ufabc.chokitus.reactive.mq.AsyncCloseable;
import br.edu.ufabc.chokitus.reactive.mq.client.ReactiveProducer;
import br.edu.ufabc.chokitus.reactive.mq.client.ReactiveReceiver;
import br.edu.ufabc.chokitus.reactive.mq.config.ReactiveConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public abstract class ReactiveFactory<C extends ReactiveConfig, R extends ReactiveReceiver<?>, P extends ReactiveProducer> implements AsyncCloseable {

	protected final C generalConfig;

	public abstract R createReceiver(final Map<String, String> config) throws Exception;
	public abstract P createProducer(final Map<String, String> config) throws Exception;

}
