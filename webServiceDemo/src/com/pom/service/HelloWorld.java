package com.pom.service;

import java.sql.SQLException;
import java.util.List;

import com.pom.jdbc.JdbcHelper;

public class HelloWorld {

	public String sayHello(String name) throws SQLException{
		return "Hello,"+name+".";
	}
	
	public String saySorry(String name) throws SQLException{
		List list = JdbcHelper.query("SELECT * FROM  aaaa WHERE rownum  <10 ");
		System.out.println(list.size());
		return "Sorry,"+name+"." + list.size() ;
	}
	
	public String getWorld(){
		return "Hello,World";
	}
	
}
