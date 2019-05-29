package com.sghy1801.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PhoneUtil {
	public static void main(String[] args) {

	}

	public static String getUrl(HashMap<String, Object> map,String mod){
		String secret_key="nbpJfp2GIzs7S2LcGLq8t80z3oJfGizUjOsp5jVBIdwp58szHIv1FvpwJZBf81Zf";
		String sign =getSing(map,secret_key);
		String url="";
		if(mod.equals("order.phone.submit")){//提交充值订单
			url="http://api.huafeiduo.com/gateway.cgi?mod=order.phone.submit";
			url+="&phone_number="+map.get("phone_number");//充值手机号
			url+="&card_worth="+map.get("card_worth");//充值面额
			url+="&sp_order_id="+map.get("sp_order_id");//商户订单号，你自己系统中的唯一订单号
			url+="&api_key="+map.get("api_key");
			url+="&notify_url="+map.get("notify_url");//回调通知地址(包含域名和文件路径的完整有效url)
			url+="&sign="+sign;//sign签名
		}else if(mod.equals("order.phone.get")){//获取充值订单信息
			url="http://api.huafeiduo.com/gateway.cgi?mod=order.phone.get";
			url+="&sp_order_id="+map.get("sp_order_id");//商户订单号，你自己系统中的唯一订单号
			url+="&api_key="+map.get("api_key");
			url+="&sign="+sign;//sign签名
		}
		return url;
	}

	//203b77233d809404e95d1d4be81b78db
	public static String getSing(HashMap<String, Object> map,String  secret_key){
		List<String> list=new ArrayList<String>();
		Set<String> keyset = map.keySet();
		for (String key : keyset) {
			list.add(key);
		}
		String mySs[]=new String[list.size()];//创建自定义排序的数组
		for (int i = 0; i < mySs.length; i++) {
			mySs[i]=list.get(i);
		}
		String stringB ="";
		String[] output=arraySort(mySs);  //排序后的数组
		for (int i = 0; i < output.length; i++) {
			stringB+=output[i]+map.get(output[i]);
		}
		System.out.println(stringB);
		String c=stringB+secret_key;
		System.out.println(c);
		String sing=md5(c);
		return sing;
	}

	private static String md5(String str) {
		//定义一个字节数组
		byte[] secretBytes = null;
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			//对字符串进行加密
			md.update(str.getBytes());
			//获得加密后的数据
			secretBytes = md.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		//将加密后的数据转换为16进制数字
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}


	public static String[] arraySort(String[] input){
		for (int i=0;i<input.length-1;i++){
			for (int j=0;j<input.length-i-1;j++) {
				if(isBiggerThan(input[j],input[j+1])){
					String temp=input[j];
					input[j]=input[j+1];
					input[j+1]=temp;
				}
			}
		}
		return input;
	}
	private static boolean isBiggerThan(String first, String second){
		if(first==null||second==null||"".equals(first) || "".equals(second)){
			System.out.println("字符串不能为空！");
			return false;
		}
		char[] arrayfirst=first.toCharArray();
		char[] arraysecond=second.toCharArray();
		int minSize = Math.min(arrayfirst.length, arraysecond.length);
		for (int i=0;i<minSize;i++) {
			if((int)arrayfirst[i]>(int)arraysecond[i]){
				return true;
			}else if((int)arrayfirst[i] < (int)arraysecond[i]){
				return false;
			}
		}
		if(arrayfirst.length>arraysecond.length){
			return true;
		}else {
			return false;
		}
	}

}
