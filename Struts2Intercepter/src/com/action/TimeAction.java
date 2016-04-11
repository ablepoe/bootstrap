package com.action;

import com.opensymphony.xwork2.ActionSupport;

public class TimeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -465269807919956186L;

	@Override
	public String execute() throws Exception{
		
		System.out.println("timeAction executed");
		
		return SUCCESS;
	}
	
}
