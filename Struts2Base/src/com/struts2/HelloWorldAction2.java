package com.struts2;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HelloWorldAction2 extends ActionSupport implements ModelDriven<User>{

	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6430083008271232162L;

	@Override
	public User getModel() {
		if(user == null){
			user = new User();
		}
		return user;
	}

	@Override
	public String execute(){
		if(user.getUsername() != null && !"".equals(user.getUsername().trim())){
			if(user.getPassword() != null && !"".equals(user.getPassword().trim())){
				return SUCCESS;
			}else{
				addFieldError("rpassword", "rpassword error");
				return INPUT;
			}
		}else{
			addFieldError("rusername", "rusername error");
			return INPUT;
		}
	}
}
