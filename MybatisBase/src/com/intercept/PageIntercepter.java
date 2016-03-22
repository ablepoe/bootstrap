package com.intercept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.entity.Page;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args=Connection.class)})
public class PageIntercepter implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//获取代理对象
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
				SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());
		//获取对应的sql对象
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		//获取配置文件中sql语句的ID
		String id = mappedStatement.getId();
		//正则表达式找到以ByPage结尾，且前面还有任意字符的id
		if(id.matches(".+ByPage$")){
			BoundSql boundSql = statementHandler.getBoundSql();
			//原始的sql语句
			String sql = boundSql.getSql();
			//查询总条数的sql语句
			String countSql = "select count(1) from ("+ sql +") a";
			//通过注解来获取connection
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement countStatement = (PreparedStatement) connection.prepareStatement(countSql);
			//获取原始sql输入的参数
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();
			
			//获取输入的参数
			Map<?,?> parameter = (Map<?, ?>) boundSql.getParameterObject();
			Page page = (Page) parameter.get("page");
			if(rs.next()){
				page.setTotalCounts(rs.getInt(1));
			}
			//改造sql，使其带上分页
			String pageSql = sql + " limit " + page.getStartIndex() + "," +page.getDbCounts();
			//更新sql
			metaObject.setValue("delegate.boundSql.sql", pageSql);
			
		}
		//通过反射来交回主权
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		//this 这个类的实例，进入次方法之前，this并没有代理的功能
		//过滤真正需要的代理执行的对象
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

		
	}

}
