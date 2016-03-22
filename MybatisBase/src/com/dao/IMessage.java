package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.MicroMessage;

public interface IMessage {

	public List<MicroMessage> getLimitListByPage(Map<String,Object> map);
	
	public List<MicroMessage> getLimitList(Map<String,Object> map);
}
