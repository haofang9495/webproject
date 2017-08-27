package com.pom.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTest {

	public static void main(String[] args) throws SQLException, ParseException {
		
		String qysj = "20180808000000" ;
		SimpleDateFormat df1=new SimpleDateFormat("yyyyMMddHHmmss");
		Date qysjDate = (Date) df1.parse(qysj);
		
		Connection conn = JdbcUnits.getConnection();
//		List list = JdbcHelper.query("SELECT * FROM  aaaa WHERE rownum  <10 ");
		String paramkey = "PRM_CBLX|PRM_TBR|PRM_ZJH|PRM_BZLB|PRM_HKXZ|PRM_MUHKXZ|PRM_QYSJ|PRM_YCQ|"
				+ "PRM_GS|PRM_HKQU|PRM_CZJG|PRM_SBYY|PRM_JFJE|PRM_AAC999";
		String[] paramkeyArr = paramkey.split("\\|");
		Map map = null;
		CallableStatement proc = conn.prepareCall("{ call APP_AS_JMCBDJ.JMYBCBJF(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
		int index = 0;
		proc.setString(++index, "1");
		proc.setString(++index, "2");
		proc.setString(++index, "3");
		proc.setString(++index, "4");
		proc.setString(++index, "5");
		proc.setString(++index, "6");
		proc.setDate(++index, new Date(2017, 1, 1));
		proc.setDate(++index, new Date(2017, 1, 1));
		proc.setFloat(++index, 9);
		proc.setString(++index, "1");
//		proc.setString(11, "1");
//		proc.setString(12, "1");
//		proc.setFloat(13, 9);
//		proc.setString(14, "1");
		proc.registerOutParameter(++index, Types.VARCHAR);//pintSuccess_cnt
		proc.registerOutParameter(++index, Types.VARCHAR);//pintSuccess_cnt
		proc.registerOutParameter(++index, Types.FLOAT);//pintSuccess_cnt
		proc.registerOutParameter(++index, Types.VARCHAR);//pintSuccess_cnt
		
		proc.execute();
		
		String str11 = proc.getString(11);
		String str12 = proc.getString(12);
		Float str13 = proc.getFloat(13);
		String str14 = proc.getString(14);
		
		System.out.println(proc.getString(12));
	}
	
	private static Map getTestMap(){
		Map map = new HashMap();
		String paramkey = "PRM_CBLX|PRM_TBR|PRM_ZJH|PRM_BZLB|PRM_HKXZ|PRM_MUHKXZ|PRM_QYSJ|PRM_YCQ|PRM_GS|PRM_HKQU|PRM_CZJG|PRM_SBYY|PRM_JFJE|PRM_AAC999";
		String[] paramkeyArr = paramkey.split("\\|");
		for(int i=0;i<paramkeyArr.length;i++){
			map.put(paramkeyArr[i], 1);
		}
		map.put("PRM_QYSJ", "12");
		map.put("PRM_YCQ", "12");
		return map;
	}

}
