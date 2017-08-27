package com.pom.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class Xmlhelper {

	
	public static void main(String args[]) throws Exception, DocumentException{
	}
	
	
	/**
	 * 根据map入参，创建xml对象后生成 xml 字符串
	 * @param map
	 * @return
	 */
	public static String requestXmlData(Map map){
		Document dom=DocumentHelper.createDocument();//创建xml文件  
		Element root=dom.addElement("request");//添加根元素,Xval  
		Element head = root.addElement("head");
		Element body = root.addElement("body");
		
		Element requestId = head.addElement("requestId");
		requestId.addText("S01"); 
		Element opertime = head.addElement("opertime");
		
		Date date = new Date();
		SimpleDateFormat df1=new SimpleDateFormat("yyyyMMddHHmmss");
		String day = df1.format(date);
		opertime.addText(day); //时分秒
		Element requestdata = body.addElement("requestdata");
		if(null != map){
			Iterator it = map.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry) it.next();  
				Element e = requestdata.addElement((String) entry.getKey());
				e.addText((String) entry.getValue());
			}
		}
		String xml=dom.asXML();  
		return xml;
	}
	
	/**
	 * 生成出参xml字符串
	 * */
	public static String responseXmlData(Map map){
		Document dom=DocumentHelper.createDocument();//创建xml文件  
		Element root=dom.addElement("response");//添加根元素,Xval  
		Element head = root.addElement("head");
		Element body = root.addElement("body");
		
		Element responseId = head.addElement("responseId");
		responseId.addText((String) map.get("requestId")); 
//		Element opertime = head.addElement("opertime");
//		
//		Date date = new Date();
//		SimpleDateFormat df1=new SimpleDateFormat("yyyyMMddHHmmss");
//		String day = df1.format(date);
//		opertime.addText(day); //时分秒
		Element requestdata = body.addElement("responsedata");
		if(null != map){
			Iterator it = map.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry) it.next();  
				Element e = requestdata.addElement((String) entry.getKey());
				e.addText((String) entry.getValue());
			}
		}
		String xml=dom.asXML();  
		return xml;
	}
	
	/**
	 * 解析出参xml 为 map 对象
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String,String> requestXmlMap(String xml) throws DocumentException{
		Document doc = DocumentHelper.parseText(xml); //创建xml文件
		Element response = doc.getRootElement();
		Element head = response.element("head");
		Element body = response.element("body");
		
		List hlist = head.elements();
		
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0;i<hlist.size();i++){
			Element e = (Element) hlist.get(i);
			map.put(e.getName(), e.getText());
		}
		if(null != body){
			Element responseData = body.element("requestdata");
			List blist = responseData.elements();
			for(int i=0;i<blist.size();i++){
				Element e = (Element) blist.get(i);
				map.put(e.getName(), e.getText());
			}
		}
		return map;
	}
	
	/**
	 * 解析出参xml 为 map 对象
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String,String> responseXmlMap(String xml) throws DocumentException{
		Document doc = DocumentHelper.parseText(xml); //创建xml文件
		Element response = doc.getRootElement();
		Element head = response.element("head");
		Element body = response.element("body");
		
		List hlist = head.elements();
		
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0;i<hlist.size();i++){
			Element e = (Element) hlist.get(i);
			map.put(e.getName(), e.getText());
		}
		if(null != body){
			Element responseData = body.element("responsedata");
			List blist = responseData.elements();
			for(int i=0;i<blist.size();i++){
				Element e = (Element) blist.get(i);
				map.put(e.getName(), e.getText());
			}
		}
		return map;
	}
	
	
	/** GBK编码 */
	public static String encode(String str) {
		String rlt = "";
		try {
			//rlt = URLEncoder.encode(str, "GBK");
			rlt = URLEncoder.encode(str, "GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return rlt;
	}
	
}
