package br.edu.ufabc.chokitus.mq.serialization;

public interface Serializer {
	String toString(byte[] bytes);
	String toBase64(byte[] bytes);
}
