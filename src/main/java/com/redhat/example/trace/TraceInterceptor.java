package com.redhat.example.trace;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.redhat.example.trace.model.TraceRecord;

@Interceptor
@Trace
public class TraceInterceptor {
	@Inject TraceFacade facade;

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ctx) throws Exception {
		try {
			Object ret = ctx.proceed();System.out.println("### TraceInterceptor called.");
			if (ctx.getMethod().getName().equals("create"))
				facade.create(new TraceRecord("SUCCESS: record="+ctx.getParameters()[0]));
			return ret;
		} catch (Exception e) {
			if (ctx.getMethod().getName().equals("create"))
				facade.create(new TraceRecord("FAILURE: record="+ctx.getParameters()[0]));
			throw e;
		}
	}

}
