package com.intercepter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class TimeActionIntercepter extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4766632866912694571L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		long beginTime = System.currentTimeMillis();
		String result = invocation.invoke();
		long endTime = System.currentTimeMillis();
		System.out.println("cost time :"+(endTime - beginTime));
		return result;
	}

}
