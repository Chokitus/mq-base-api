package br.edu.ufabc.chokitus.mq.benchmark;

import java.util.HashMap;

public class ConfigurationProperties extends HashMap<String, Object> {

	public <T> T getProp(final String key) {
		return (T) get(key);
	}

	public <T> T getPropOrDefault(final String key, final T defaultValue) {
		final T prop = getProp(key);
		return prop == null ? defaultValue : prop;
	}

	public Boolean getBooleanPropOrDefault(final String key, final Boolean defaultValue) {
		final String prop = getProp(key);
		return prop == null ? defaultValue : Boolean.valueOf(prop);
	}

	public Integer getAsInteger(final String key) {
		String prop = getProp(key);
		return Integer.valueOf(prop);
	}

	public Boolean getAsBoolean(final String key) {
		return Boolean.valueOf(getProp(key));
	}
}
