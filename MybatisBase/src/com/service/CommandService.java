package com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.dao.ICommandContent;
import com.db.DBUtil;
import com.entity.CommandContent;

public class CommandService {

	public List<CommandContent> getAllCommandContent(){
		DBUtil dbUtil = new DBUtil();
		List<CommandContent> result = new ArrayList<CommandContent>();
		SqlSession sqlSession;
		try {
			sqlSession = dbUtil.getSqlsession();
			ICommandContent iCommandContent = sqlSession.getMapper(ICommandContent.class);
			result = iCommandContent.getAllCommandContent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void insertCommandContent(String[] ids,String[] contents){
		if(ids != null && contents != null){
			DBUtil dbUtil = new DBUtil();
			SqlSession sqlSession;
			List<CommandContent> commandContents = new ArrayList<CommandContent>();
			CommandContent commandContent;
			for (int i = 0; i < ids.length; i++) {
				commandContent = new CommandContent();
				commandContent.setCommand_id(Integer.parseInt(ids[i]));
				commandContent.setContent(contents[i]);
				commandContents.add(commandContent);
			}
			try {
				sqlSession = dbUtil.getSqlsession();
				ICommandContent iCommandContent =sqlSession.getMapper(ICommandContent.class);
				iCommandContent.insertCommandContent(commandContents);
				sqlSession.commit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		CommandService commandService = new CommandService();
//		List<CommandContent> li = commandService.getAllCommandContent();
//		for (CommandContent commandContent : li) {
//			System.out.println(JSON.toJSONString(commandContent));
//		}
		String[] ids = {"1","2"};
		String[] contents = {"3","4"};
		commandService.insertCommandContent(ids, contents);
	}
}
