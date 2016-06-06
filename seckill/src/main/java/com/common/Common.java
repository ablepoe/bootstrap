package com.common;

/**
 * 通用属性类
 * @author hanliang
 *
 */
public class Common {

	public static int STATE = 1;
	public static int TIMEOVER = -1;
	public static int NOTSTARTED = -2;
	public static int KILLING = 1;
	
	public static String SALT = "@#!#%$%dsf9";
	
	public static String getInfo(int code){
		String stateInfo = null;
		switch(code){
		case -1: stateInfo = "time over!";
			break;
		case -2: stateInfo = "not begin";
			break;
		case 1: stateInfo = "killing";
			break;
		default: stateInfo = "";
			break;
		}
		return  stateInfo;
	}
}
