package com.intercepter;

import java.util.Map;

import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserAuthActionIntercepter extends AbstractInterceptor implements ModelDriven<User>{

	private User user;
	private Map<String, Object> session;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3750226078171136995L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		session = context.getSession();
		if("true".equals(session.get("userAuth")) ){
			String result = invocation.invoke();
			return result;
		}else{
			return "error";
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
