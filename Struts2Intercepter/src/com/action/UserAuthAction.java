package com.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAuthAction extends ActionSupport implements SessionAware,ModelDriven<User>{

	private User user;
	private Map<String,Object> session;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7520906382761623620L;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public String execute() throws Exception{
		if("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())){
			session.put("userAuth", "true");
			return SUCCESS;
		}else{
			return ERROR;			
		}
	}

	@Override
	public User getModel() {
		if(user == null){
			user = new User();
		}
		return user;
	}
	
}
