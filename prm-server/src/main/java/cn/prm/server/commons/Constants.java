package cn.prm.server.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @Title: Constants.java
 * @Package: cn.prm.server.commons
 * @Description: 常量
 * @author yyao
 * @date 2016年10月19日 下午5:25:05
 * @version v1.0
 */
public class Constants {

	/**
	 * 
	 * @Title: Constants.java
	 * @Package: cn.prm.server.commons
	 * @Description: session key
	 * @author yyao
	 * @date 2016年10月19日 下午5:25:18
	 * @version v1.0
	 */
	public static class SESSION {
		/**
		 * @field LOGIN_USER : 
		 */
		public static final String LOGIN_USER = "cn.prm.server.commons.Constants.SESSION.LOGIN_USER";
	}

	/**
	 * 
	 * @Title: Constants.java
	 * @Package: cn.prm.server.commons
	 * @Description: response code
	 * @author yyao
	 * @date 2016年10月19日 下午5:25:33
	 * @version v1.0
	 */
	public static class RESPONSE_CODE {
		/**
		 * @field CODE_SUCCESS : 
		 */
		public static final int CODE_SUCCESS = 100;
		/**
		 * @field CODE_FAILURE : 
		 */
		public static final int CODE_FAILURE = 200;
		/**
		 * @field CODE_NEED_LOGIN : 
		 */
		public static final int CODE_NEED_LOGIN = 300;

	}

	/**
	 * 
	 * @Title: Constants.java
	 * @Package: cn.prm.server.commons
	 * @Description: db status
	 * @author yyao
	 * @date 2016年10月19日 下午5:25:42
	 * @version v1.0
	 */
	public static class DB_STATUS {
		/**
		 * @field STATUS_ACTIVE : 
		 */
		public static final int STATUS_ACTIVE = 100;
		/**
		 * @field STATUS_INACTIVE : 
		 */
		public static final int STATUS_INACTIVE = 200;
	}

	/**
	 * 
	 * @Title: Constants.java
	 * @Package: cn.prm.server.commons
	 * @Description: ModelAndView key
	 * @author yyao
	 * @date 2016年10月19日 下午5:25:52
	 * @version v1.0
	 */
	public static class MAV_KEYS {
		/**
		 * @field USER_NAME : 
		 */
		public static final String USER_NAME = "userName";
	}

	/**
	 * 
	 * @Title: Constants.java
	 * @Package: cn.prm.server.commons
	 * @Description: 联系方式枚举
	 * @author yyao
	 * @date 2016年10月19日 下午5:26:00
	 * @version v1.0
	 */
	public static enum CONTACT_TYPE {
		
		/**
		 * @field Phone : 
		 */
		Phone("手机", 1001), 
		/**
		 * @field Email : 
		 */
		Email("邮箱", 1002), 
		/**
		 * @field WeiXin : 
		 */
		WeiXin("微信", 1003), 
		/**
		 * @field QQ : 
		 */
		QQ("QQ", 1004);

		private String name;
		private int code;

		private CONTACT_TYPE(String name, int code) {
			this.name = name;
			this.code = code;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the code
		 */
		public int getCode() {
			return code;
		}

		/**
		 * @param code the code to set
		 */
		public void setCode(int code) {
			this.code = code;
		}

		/** 
		 * @Title: getAllList 
		 * @Description: 
		 * @return
		 * @throws 
		 */
		public static List<CONTACT_TYPE> getAllList() {
			List<CONTACT_TYPE> list = new ArrayList<>();
			Collections.addAll(list, CONTACT_TYPE.values());
			return list;
		}

		/** 
		 * @Title: getName 
		 * @Description: 
		 * @param code
		 * @return
		 * @throws 
		 */
		public static String getName(int code) {
			for (CONTACT_TYPE type : CONTACT_TYPE.values()) {
				if (type.code == code) {
					return type.name;
				}
			}
			return null;
		}

		/** 
		 * @Title: getCode 
		 * @Description: 
		 * @param name
		 * @return
		 * @throws 
		 */
		public static Integer getCode(String name) {
			for (CONTACT_TYPE type : CONTACT_TYPE.values()) {
				if (type.name.equals(name)) {
					return type.code;
				}
			}
			return null;
		}
	}

}
