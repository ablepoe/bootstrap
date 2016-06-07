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
	public static int KILLOBJNOTEXIST = -3;
	
	public static String SALT = "@#!#%$%dsf9";
	
	public static boolean SUCCESS = true;
	public static boolean FAIL = false;
	
	public static String getInfo(int code){
		String stateInfo = null;
		switch(code){
		case -1: stateInfo = "time over!";
			break;
		case -2: stateInfo = "not begin";
			break;
		case 1: stateInfo = "killing";
			break;
		case -3: stateInfo = "seckill object not exist";
			break;
		default: stateInfo = "";
			break;
		}
		return  stateInfo;
	}
}
