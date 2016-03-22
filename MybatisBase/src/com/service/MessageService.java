package com.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.dao.MessageDao;
import com.entity.Command;
import com.entity.MicroMessage;
import com.entity.MicroMessageParameter;
import com.entity.Page;
import com.iconst.Iconst;

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
		if(ID != null && !"".equals(ID.trim())){
			MessageDao messageDao = new MessageDao();
			messageDao.removeOneMessage(ID);	
		}
	}
	
	public void removeBatchMessage(String[] IDs){
		if(IDs != null && IDs.length > 0){
			List<String> idList = new ArrayList<String>();
			for(String id : IDs){
				idList.add(id);
			}
			MessageDao messageDao = new MessageDao();
			messageDao.removeBatchMessage(idList);	
		}
	}
	
	public void insertOneMessage(String command,String description){
		if(command != null && !"".equals(command.trim()) && description != null && !"".equals(description.trim()) ){
			MicroMessageParameter microMessageParameter = new MicroMessageParameter();
			microMessageParameter.setCommand(command);
			microMessageParameter.setDescription(description);
			MessageDao messageDao = new MessageDao();
			messageDao.insertOneMessage(microMessageParameter);
		}
	}
	
	public String queryMessageByMybatis(String command){
		
		StringBuffer sb = new StringBuffer();
		
		if(Iconst.HELP_COMMAND.equals(command)){
			return queryHelpMessageByMybatis(sb,command);
		}
		
		List<MicroMessage> results = this.queryMessageListByMybatis(command, null);
		if(results.size() > 0){
			sb.append("your answer is "+results.get(0).getDescription()+"<br/>");
		}else{
			sb.append(Iconst.UNKNOWN_COMMAND);
		}
		return sb.toString();
	}
	
	private String queryHelpMessageByMybatis(StringBuffer sb, String command){
		List<MicroMessage> results = this.queryMessageListByMybatis(null, null);
		if(results.size() > 0){
			sb.append("you have the above commands to use:<br/>");
			for (int i = 0; i < results.size(); i++) {
				sb.append("input "+results.get(i).getCommand()+" shows "+results.get(i).getDescription());
				sb.append("<br/>");
			}
		}
		return sb.toString();
	}
	
	public String queryCommandByMybatis(String command,String description){
		StringBuffer sb = new StringBuffer();
		if(Iconst.HELP_COMMAND.equals(command)){
			return queryHelpMessageByMybatis(sb,command);
		}
		MessageDao messageDao = new MessageDao();
		List<Command> commandList = messageDao.queryRandomCommand(command,description);
		if(commandList.size() > 0){
			Random random = new Random();
			int randomColoum = random.nextInt(commandList.get(0).getContents().size());
			sb.append("your answer is "+ commandList.get(0).getContents().get(randomColoum).getContent() +"</br>");
		}else{
			sb.append(Iconst.UNKNOWN_COMMAND);
		}
		return sb.toString();
	}
	
	public String queryCommandByMybatis(String command){
		return this.queryCommandByMybatis(command, null);
	}
	
	public Map<String,Object> getLimitList(String command,String description,String targetPage) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		MessageDao messageDao = new MessageDao();
		int totalCounts = 0;
		try {
			totalCounts = messageDao.queryMessageList(null, null).size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		MicroMessageParameter microMessageParameter = new MicroMessageParameter();
		microMessageParameter.setCommand(command);
		microMessageParameter.setDescription(description);
		
		Page page = new Page();
		page.setTotalCounts(totalCounts);
		if(targetPage == null || "".equals(targetPage)){
			targetPage = "1";
		}
		page.setCurrentPage(Integer.parseInt(targetPage));
		page.count();
		
		parameter.put("page", page);
		parameter.put("microMessageParameter", microMessageParameter);
		
		map.put("microMessageList", messageDao.getLimitListByPage(parameter));
		map.put("page", page);
		
		return map;
	}
	
	public Map<String,Object> getLimitListByPage(String command,String description,String targetPage) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		MessageDao messageDao = new MessageDao();
		
		MicroMessageParameter microMessageParameter = new MicroMessageParameter();
		microMessageParameter.setCommand(command);
		microMessageParameter.setDescription(description);
		
		Page page = new Page();
		if(targetPage == null || "".equals(targetPage.trim())){
				targetPage = "1";
		}
		page.setCurrentPage(Integer.parseInt(targetPage));

		parameter.put("page", page);
		parameter.put("microMessageParameter", microMessageParameter);
		
		map.put("microMessageList", messageDao.getLimitListByPage(parameter));
		map.put("page", page);
		
		return map;
	}
	
	public static void main(String[] args) {
		MessageService messageService = new MessageService();
		Map<String,Object> map = messageService.getLimitListByPage(null, null, "2");
		java.util.Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry entry = it.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			System.out.println(JSON.toJSONString(entry.getValue()));
			
		}
//		System.out.println(messageService.queryCommandByMybatis(null,"段子"));
//		System.out.println(messageService.queryCommandByMybatis("查看","精彩"));
	}
}
