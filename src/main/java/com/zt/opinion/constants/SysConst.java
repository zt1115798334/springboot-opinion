package com.zt.opinion.constants;

/**
 * 
 * <p>Title: SysConst</p>
 * <p>Description: 系统常量类</p>
 * @author wjc
 * @date 2016年12月26日
 */
public class SysConst {
	
	/**
	 * 默认每页记录数
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final int DEFAULT_PAGE_NUMBER = 1;
	
	public static final String ENCODING_UTF_8 = "UTF-8";
	
	/**
	 * 
	 * <p>Title: EmotionType</p>
	 * <p>Description: 情感类型枚举类</p>
	 * @author Wangjianchun
	 * @date 2017年7月5日
	 */
	public static enum EmotionType{
		
		POSITIVE("positive", "正面"),
		NEUTRAL("neutral", "中性"),
		NEGATIVE("negative", "负面");
		
		private String code;
		private String name;
		
		private EmotionType(String code, String name){
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

	}
	
	/**
	 * 
	 * <p>Title: MediaType</p>
	 * <p>Description: 媒体类型枚举类</p>
	 * @author Wangjianchun
	 * @date 2017年7月5日
	 */
	public static enum MediaType{
		
		NEWS("新闻"),
		WECHAT("微信"),
		WEIBO("微博"),
		BLOG("博客"),
		FORUM("论坛");

		private String name;
		
		private MediaType(String name){
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}
	
	/**
	 * 
	 * <p>Title: PressureType</p>
	 * <p>Description: 舆情压力类型枚举类</p>
	 * @author Wangjianchun
	 * @date 2017年7月7日
	 */
	public static enum PressureType{
		
		OVERALL("整体压力"),
		POSITIVE("正面压力"),
		NEGATIVE("负面压力");

		private String name;
		
		private PressureType(String name){
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}
	
}
