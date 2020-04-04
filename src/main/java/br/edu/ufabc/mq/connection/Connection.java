package br.edu.ufabc.mq.connection;

import java.io.Closeable;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Connection<C> {
	private C connectionInstance;

	public Connection(final C connection) {
		this.connectionInstance = connection;
	}

	public void close() throws IOException {
		((Closeable) connectionInstance).close();
	}
}
