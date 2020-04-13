//package com.cctv.project.noah.system.config;
//
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.ParameterMapping;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.type.TypeHandlerRegistry;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Field;
//import java.sql.Statement;
//import java.text.DateFormat;
//import java.util.*;
//
//@Configuration
//@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
//		@Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
//		@Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
//@Component
//public class MybatisShowSQLInterceptor implements Interceptor {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(MybatisShowSQLInterceptor.class);
//
//	@Override
//	public Object intercept(Invocation invocation) {
//		// 开始时间
//		try {
//			Object target = invocation.getTarget();
//			Field delegate=target.getClass().getDeclaredField("delegate");
//			delegate.setAccessible(true);
//			Object delegeObject=delegate.get(target);
//			Field[] fields=delegeObject.getClass().getSuperclass().getDeclaredFields();
//			Field mappField=delegeObject.getClass().getSuperclass().getDeclaredField("mappedStatement");
//			mappField.setAccessible(true);
//			MappedStatement mappedStatement= (MappedStatement) mappField.get(delegeObject);
//			Object parameter = null;
//			if (invocation.getArgs().length > 1) {
//				parameter = invocation.getArgs()[1];
//			}
//			String sqlId = mappedStatement.getId();
//			BoundSql boundSql = mappedStatement.getBoundSql(parameter);
//			org.apache.ibatis.session.Configuration configuration = mappedStatement.getConfiguration();
//			Object returnValue = null;
//			long start = System.currentTimeMillis();
//			returnValue = invocation.proceed();
//			long end = System.currentTimeMillis();
//			long time = (end - start);
//			if (time > 1) {
//				String sql = getSql(configuration, boundSql, sqlId, time);
//				LOGGER.info(sql);
//			}
//			return returnValue;
//
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		long start = System.currentTimeMillis();
//		invocation.getArgs();
//		try {
//			return invocation.proceed();
//		} catch (Exception e) {
//			LOGGER.error("执行失败！", e);
//			return null;
//		} finally {
//			long end = System.currentTimeMillis();
//			long time = end - start;
//			LOGGER.info("cost time {}ms", time);
//		}
//	}
//
//	public static String getSql(org.apache.ibatis.session.Configuration configuration, BoundSql boundSql,
//								String sqlId, long time) {
//		String sql = showSql(configuration, boundSql);
//		StringBuilder str = new StringBuilder(100);
//		str.append(sqlId);
//		str.append(":");
//		str.append(sql);
//		str.append(":");
//		str.append(time);
//		str.append("ms");
//		return str.toString();
//	}
//
//	private static String getParameterValue(Object obj) {
//		String value = null;
//		if (obj instanceof String) {
//			value = "'" + obj.toString() + "'";
//		} else if (obj instanceof Date) {
//			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
//			value = "'" + formatter.format(new Date()) + "'";
//		} else {
//			if (obj != null) {
//				value = obj.toString();
//			} else {
//				value = "";
//			}
//
//		}
//		return value;
//	}
//
//	public static String showSql(org.apache.ibatis.session.Configuration configuration, BoundSql boundSql) {
//		Object parameterObject = boundSql.getParameterObject();
//		List<ParameterMapping> parameterMappings = boundSql
//				.getParameterMappings();
//		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
//		if (parameterMappings.size() > 0 && parameterObject != null) {
//			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
//			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
//				sql = sql.replaceFirst("\\?",getParameterValue(parameterObject));
//			} else {
//				MetaObject metaObject = configuration.newMetaObject(parameterObject);
//				for (ParameterMapping parameterMapping : parameterMappings) {
//					String propertyName = parameterMapping.getProperty();
//					if (metaObject.hasGetter(propertyName)) {
//						Object obj = metaObject.getValue(propertyName);
//						sql = sql.replaceFirst("\\?", getParameterValue(obj));
//					} else if (boundSql.hasAdditionalParameter(propertyName)) {
//						Object obj = boundSql.getAdditionalParameter(propertyName);
//						sql = sql.replaceFirst("\\?", getParameterValue(obj));
//					}
//				}
//			}
//		}
//		return sql;
//	}
//
//
//	@Override
//	public Object plugin(Object arg0) {
//		return Plugin.wrap(arg0, new MybatisShowSQLInterceptor());
//	}
//
//	@Override
//	public void setProperties(Properties arg0) {
//	}
//}
//
