package br.edu.ufabc.mq.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Message {
	private String destination;
	private byte[] content;
}
 