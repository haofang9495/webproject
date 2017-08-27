package com.pom.client;


import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.pom.util.Xmlhelper;

/**
 * 调用
 * */
public class AxisUtil {
	public static void main(String[] args) {
		Map map = initMap();
		String xmlStr=Xmlhelper.requestXmlData(map);
		String url="http://127.0.0.1:8080/webServiceDemo/services/SiInterfaceAction";
		String method="siInterface";
		AxisUtil.sendService(xmlStr,url,method);
	}
	public static String sendService(String xmlStr,String url,String method){
		String outxml=null;
		try {
			
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference targetEPR = new EndpointReference(url);
			options.setTo(targetEPR);
			// 在创建QName对象时，QName类的构造方法的第一个参数表示WSDL文件的命名空间名，也就是<wsdl:definitions>元素的targetNamespace属性值
			 QName opAddEntry = new QName("http://service.pom.com",method);
			 // 参数，如果有多个，继续往后面增加即可，不用指定参数的名称
			 Object[] opAddEntryArgs = new Object[] {xmlStr};
			 // 返回参数类型，这个和axis1有点区别
			 // invokeBlocking方法有三个参数，其中第一个参数的类型是QName对象，表示要调用的方法名；
			 // 第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；
			 // 第三个参数表示WebService方法的返回值类型的Class对象，参数类型为Class[]。
			 // 当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}
			 // 如果被调用的WebService方法没有返回值，应使用RPCServiceClient类的invokeRobust方法，
			 // 该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同
			 Class[] classes = new Class[] { String.class };
			 outxml=(String)serviceClient.invokeBlocking(opAddEntry,opAddEntryArgs, classes)[0];
			 System.out.println(outxml); 


		} catch (Exception e) {
			e.printStackTrace();
			long end = System.currentTimeMillis();
		}
		return outxml;
	}
	
	
	/**
	 * 调试数据
	 * */
	private static Map<String,Object> initMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("param1", "1");
		map.put("param2", "1");
		map.put("param3", "20170827000000");
		return map;
	}
}
