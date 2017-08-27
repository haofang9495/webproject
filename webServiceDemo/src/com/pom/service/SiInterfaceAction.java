package com.pom.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import com.pom.jdbc.JdbcHelper;
import com.pom.jdbc.JdbcUnits;
import com.pom.util.ConvertDemo;
import com.pom.util.Xmlhelper;

public class SiInterfaceAction {
	
	/**
	 * @throws DocumentException 
	 * @throws ParseException 
	 * */
	public String siInterface(String xmlString) throws SQLException, DocumentException, ParseException{
		Map<String,String> map = Xmlhelper.requestXmlMap(xmlString);
		String requestId = map.get("requestId"); //不同的requestId 可以为以后 扩展不同的service 接口。
//		if(requestId.equals("S01")){
//			test1(map);
//		}
		map.put("result", "返回结果");
		
		return Xmlhelper.responseXmlData(map);
		
	}

	/**
	 * @throws ParseException 
	 * @throws DocumentException 
	 * */
	public String test1(Map<String,String> map) throws SQLException, ParseException, DocumentException{
		Connection conn = JdbcUnits.getConnection();
		//调用存储过程
		CallableStatement proc = conn.prepareCall("{ call pkg.procedure(?,?,?,?) }");
		
		int index = 0;
		proc.setString(++index,  map.get("param1") );
		proc.setString(++index,  map.get("param2") );
		
		String qysj =  map.get("param3") ;
		Date qysjDate = ConvertDemo.StrToDate(qysj,"yyyyMMddHHmmss");
		proc.setDate(++index, new java.sql.Date(qysjDate.getTime())); //日期参数
		//出参
		proc.registerOutParameter(++index, Types.VARCHAR);//pintSuccess_cnt
		
		proc.execute();
		
		String result = proc.getString(4);
		
		map.put("result", result);
		return Xmlhelper.responseXmlData(map);
	}
	
}
