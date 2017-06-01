package com.redhat.example.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerFactory {
	@Produces
	public Logger getLogger(InjectionPoint injectionPoint) throws Exception {
        // インジェクション・ポイントにおけるクラス名を取得する。
		String name = injectionPoint.getMember().getDeclaringClass().getName();
		return Logger.getLogger(name);
	}
}
