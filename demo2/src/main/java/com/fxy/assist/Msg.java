package com.fxy.assist;

import java.util.HashMap;
import java.util.Map;
/**
 * @Title: Msg
 * @Description:返回类型的辅助类 
 * @author fxy 
 * @date 2018年3月5日
 */
public class Msg {
	private int code;
	private String msg;
	private Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * @Title: success() 
	 * @Description: code为100则msg成功
	 * @return       
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月5日
	 */
	public static Msg success(){
		Msg msg = new Msg();
		msg.setCode(100);
		msg.setMsg("成功");
		return msg;
	}
	
	/**
	 * @Title: error() 
	 * @Description: code为200则msg失败
	 * @return       
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月5日
	 */
	public static Msg error(){
		Msg msg = new Msg();
		msg.setCode(200);
		msg.setMsg("失败");
		return msg;
	}
	public Msg	 add(String key,Object value) {
		map.put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
