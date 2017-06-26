package com.zt.opinion.utils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zt.opinion.constants.SysConst;


/**
 * 
 * <p>Title: StringUtils</p>
 * <p>Description: 字符串工具类</p>
 * @author wjc
 * @date 2017年4月22日
 */
public class StringUtils {

	private StringUtils() {
	}
	
	/**
	 * 
	 * <p>Description: 根据数量和边界值生成随机的数据，用于测试或者演示</p>
	 * @param quantity
	 * @param bound
	 * @return
	 * @author wjc
	 * @date 2017年4月22日
	 */
	public static List<Integer> generateData(int quantity, int bound){
		List<Integer> result = new ArrayList<Integer>();
		Random random = new Random();
		for(int i=0; i<quantity; i++){
			result.add(random.nextInt(bound));
		}
		
		return result;
	}
	
	/**
	 * 
	 * <p>Description: 根据数量和边界值生成随机的数据，生成的数据按照正序排序，用于测试或者演示</p>
	 * @param quantity
	 * @param bound
	 * @return
	 * @author wjc
	 * @date 2017年4月22日
	 */
	public static List<Integer> generateDataOrderByAsc(int quantity, int bound){
		List<Integer> result = generateData(quantity, bound);
		result.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		
		return result;
	}
	
	/**
	 * 
	 * <p>Description: 根据数量和边界值生成随机的数据，生成的数据按照降序排序，用于测试或者演示</p>
	 * @param quantity
	 * @param bound
	 * @return
	 * @author wjc
	 * @date 2017年4月22日
	 */
	public static List<Integer> generateDataOrderByDesc(int quantity, int bound){
		List<Integer> result = generateData(quantity, bound);
		result.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		
		return result;
	}
	
	/**
	 * 
	 * <p>Description: 根据指定的边界值生成一个随机数值</p>
	 * @param bound
	 * @return
	 * @author wjc
	 * @date 2017年4月23日
	 */
	public static Integer randomNumber(int bound){
		Random random = new Random();
		return random.nextInt(bound);
	}
	
	/**
	 * 
	 * <p>Description: 获取34个省份列表</p>
	 * @return
	 * @author wjc
	 * @date 2017年5月4日
	 */
	public static List<String> getProvinceList(){
		String[] provincesText = {"上海", "河北", "山西", "内蒙古", "辽宁", 
				"吉林","黑龙江",  "江苏", "浙江", "安徽", "福建", "江西", 
				"山东","河南", "湖北", "湖南", "广东", "广西", "海南", "四川", 
				"贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆", 
				"北京", "天津", "重庆", "香港", "澳门", "台湾"};
		return Arrays.asList(provincesText);
	}
	
	/**
	 * 
	 * <p>Description: 对于参数使用UTF-8进行解码</p>
	 * @param params
	 * @return
	 * @author wjc
	 * @date 2017年2月22日
	 */
	public static String decode(String params){
		String result = null;
		try {
			result = URLDecoder.decode(params, SysConst.ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照',
		//'分割"***.***.***.***".length() = 15
		if (ipAddress != null && ipAddress.length() > 15) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	
	/**
	 * 根据用户请求判断用户使用的是否是IE浏览器
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isIE(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent").toUpperCase();
		boolean isIE = ((agent != null && agent.indexOf("MSIE") != -1)
				|| (null != agent && -1 != agent.indexOf("LIKE GECKO"))); // 判断版本,后边是判断IE11的

		return isIE;
	}
	
	/**
	 * 将参数转化为JSONObject对象 参数格式:username=a111&age=12
	 * 
	 * @param param
	 * @return JSONObject
	 */
	public static JSONObject paramToJson(String param) {
		if (param == null || "".equals(param.trim())) {
			return null;
		}
		JSONObject jsonObj = new JSONObject();
		try {
			String decodedParam = URLDecoder.decode(param, SysConst.ENCODING_UTF_8);
			String[] params = decodedParam.split("&");
			for (String str : params) {
				try {
					String[] strs = str.split("=");
					if (strs.length == 2) {
						if (strs[1].startsWith("[")) {
							JSONArray jsons = JSONArray.parseArray(strs[1]);
							jsonObj.put(strs[0], jsons);
						} else if (strs[1].startsWith("{")) {
							JSONObject jsons = JSONObject.parseObject(strs[1]);
							jsonObj.put(strs[0], jsons);
						} else {
							jsonObj.put(strs[0], strs[1].replace("\"", ""));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
	
	public static boolean isEmpty(String str){
		return org.apache.commons.lang3.StringUtils.isEmpty(str);
	}
	
	public static boolean isNotEmpty(String str){
		return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
	}
	
	/**
	 * 
	 * <p>Description: 统计关键词在内容中出现的次数</p>
	 * @param content
	 * @param keyword
	 * @return
	 * @author wjc
	 * @date 2017年5月22日
	 */
	public static int countOccurrenceNumber(String content, String keyword){
		if(isEmpty(content) || isEmpty(keyword)){
			return 0;
		}
		int result = 0;
		Pattern pattern = Pattern.compile("("+keyword+")");
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){
			result++;
		}
		return result;
	}
	
}
