package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.db.DBUtil;
import com.entity.Command;
import com.entity.MicroMessage;
import com.entity.MicroMessageParameter;
import com.entity.Page;

/**
 * 
 * @author hanliang
 * message表相关查询
 */
public class MessageDao {

	public List<MicroMessage> queryMessageList(String command, String description) throws SQLException  {
		//建立数据库链接
		Connection conn = DBUtil.getConn();
		StringBuilder sb = new StringBuilder();
		sb.append(" select ID,COMMAND,DESCRIPTION,CONTENT from message");
		sb.append(" where 1=1");
		List<String> paramList = new ArrayList<String>();
		//拼接sql字符串
		if(command != null && !"".equals(command.trim()) ){
			sb.append(" and COMMAND = ?");
			paramList.add(command);
		}
		if(description != null && !"".equals(description.trim())){
			sb.append(" and DESCRIPTION like '%' ? '%'");
			paramList.add(description);
		}
		//加载sql
		PreparedStatement pstmt = DBUtil.getPstmt(conn, sb.toString());
		for (int i = 0; i < paramList.size(); i++) {
			pstmt.setString(i+1, paramList.get(i));
		}
		//执行sql
		ResultSet rs = pstmt.executeQuery();
		List<MicroMessage> microMessageList = new ArrayList<MicroMessage>();
		MicroMessage microMessage;
		//得到返回值
		while(rs.next()){
			microMessage = new MicroMessage();
			microMessage.setId(rs.getString("ID"));
			microMessage.setCommand(rs.getString("COMMAND"));
			microMessage.setDescription(rs.getString("DESCRIPTION"));
			microMessage.setContent(rs.getString("CONTENT"));
			microMessageList.add(microMessage);
		}
		return microMessageList;
	}
	
	public List<MicroMessage> queryMessageListByMybatis(String command,String description){
		DBUtil dbUtil = new DBUtil();
		SqlSession sqlSession = null;
		List<MicroMessage> microMessage = new ArrayList<MicroMessage>();
		MicroMessageParameter microMessageParameter = new MicroMessageParameter();
		microMessageParameter.setCommand(command);
		microMessageParameter.setDescription(description);
		try {
			//获取sqlSession
			sqlSession = dbUtil.getSqlsession();
			//执行sql
			microMessage = sqlSession.selectList("Message.getAllList",microMessageParameter);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(sqlSession != null){
				sqlSession.close();	
			}
		}
		return microMessage;
	}
	
	public void removeOneMessage(String ID){
		DBUtil dbUtil = new DBUtil();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbUtil.getSqlsession();
			sqlSession.delete("Message.removeOne", ID);
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(sqlSession != null){
				sqlSession.close();	
			}
		}
	}
	
	public void removeBatchMessage(List<String> IDs){
		DBUtil dbUtil = new DBUtil();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbUtil.getSqlsession();
			sqlSession.delete("Message.removeBatch",IDs);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
	}
	
	public void insertOneMessage(MicroMessageParameter microMessageParameter){
		DBUtil dbUtil = new DBUtil();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbUtil.getSqlsession();
			sqlSession.insert("Message.insertOne",microMessageParameter);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
	}
	
	public List<Command> queryRandomCommand(String command,String description){
		DBUtil dbUtil = new DBUtil();
		SqlSession sqlSession = null;
		List<Command> commandList = new ArrayList<Command>();
		try {
			sqlSession = dbUtil.getSqlsession();
			MicroMessageParameter messageParameter = new MicroMessageParameter();
			messageParameter.setCommand(command);
			messageParameter.setDescription(description);
			commandList = sqlSession.selectList("Command.getAllList",messageParameter);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return commandList;
	}
	
	public List<MicroMessage> getLimitListByPage(Map<String,Object> parameter){
		DBUtil dbUtil = new DBUtil();
		List<MicroMessage> result = new ArrayList<MicroMessage>();
		SqlSession sqlSession;
		try {
			sqlSession = dbUtil.getSqlsession();
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			result = imessage.getLimitListByPage(parameter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		MessageDao dao = new MessageDao();
		MicroMessageParameter microMessageParameter = new MicroMessageParameter();
		microMessageParameter.setCommand(null);
		microMessageParameter.setDescription(null);
		Page page = new Page();
//		page.setStartIndex(0);
//		page.setDbCounts(5);
		Map map = new HashMap();
		map.put("page", page);
		map.put("microMessageParameter", microMessageParameter);
		System.out.println(dao.getLimitListByPage(map).size());
	}
}
