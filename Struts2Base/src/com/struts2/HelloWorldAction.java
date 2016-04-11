package com.struts2;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1070705225162601567L;
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String execute() throws Exception{
		System.out.println("HelloWorldAction executed");
		System.out.println("username is "+user.getUsername());
		System.out.println("password is "+user.getPassword());
		if(user.getUsername() != null && !"".equals(user.getUsername().trim())){
			if(user.getPassword() != null && !"".equals(user.getPassword().trim())){
				return SUCCESS;
			}else{
				addFieldError("rpassword", "password error");
				return INPUT;
			} 
		}else {
			addFieldError("rusername", "username error");
			return INPUT;
		}
	}
	
	public String add(){
		return "add";
	}

	public String update(){
		return "update";
	}
}
