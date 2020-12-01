package br.edu.ufabc.chokitus.reactive.mq.config;

import lombok.RequiredArgsConstructor;

/**
 * The sole purpose of this class is to require a implementation to all services, in order to reduce casting while configuring factories and clients.
 */
public abstract class ReactiveConfig {

	public interface ReactiveProperty {
		String v();
	}

}
