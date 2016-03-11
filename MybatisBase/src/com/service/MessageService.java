package com.service;

import java.util.List;

import com.dao.MessageDao;
import com.entity.MicroMessage;

/**
 * 
 * @author hanliang
 * message相关服务
 */
public class MessageService {

	public List<MicroMessage> queryMessageListByMybatis(String command,String description){
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageListByMybatis(command, description);
	}
	
	public void removeOneMessage(String ID){
		MessageDao messageDao = new MessageDao();
		messageDao.removeOneMessage(ID);
	}
}
