package com.redhat.example.util;

import java.util.Properties;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class SystemPropertiesFactory {

	@Produces
	@Named("props")
	public Properties getSystemProperties() {
		return System.getProperties();
	}
}
