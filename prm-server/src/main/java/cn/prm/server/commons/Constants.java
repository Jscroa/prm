package cn.prm.server.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 常量
 * 
 * @author yyao
 *
 */
public class Constants {

	/**
	 * session key
	 * 
	 * @author yyao
	 *
	 */
	public static class SESSION {
		public static final String LOGIN_USER = "cn.prm.server.commons.Constants.SESSION.LOGIN_USER";
	}

	/**
	 * response code
	 * 
	 * @author yyao
	 *
	 */
	public static class RESPONSE_CODE {
		public static final int CODE_SUCCESS = 100;
		public static final int CODE_FAILURE = 200;
		public static final int CODE_NEED_LOGIN = 300;

	}

	/**
	 * db status
	 * 
	 * @author yyao
	 *
	 */
	public static class DB_STATUS {
		public static final int STATUS_ACTIVE = 100;
		public static final int STATUS_INACTIVE = 200;
	}

	/**
	 * ModelAndView key
	 * 
	 * @author yyao
	 *
	 */
	public static class MAV_KEYS {
		public static final String USER_NAME = "userName";
	}

	/**
	 * 联系方式枚举
	 * @author yyao
	 *
	 */
	public static enum CONTACT_TYPE {
		
		Phone("手机", 1001), Email("邮箱", 1002), WeiXin("微信", 1003), QQ("QQ", 1004);

		private String name;
		private int code;

		private CONTACT_TYPE(String name, int code) {
			this.name = name;
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public int getCode() {
			return code;
		}

		public static List<CONTACT_TYPE> getAllList() {
			List<CONTACT_TYPE> list = new ArrayList<>();
			Collections.addAll(list, CONTACT_TYPE.values());
			return list;
		}

		public static String getName(int code) {
			for (CONTACT_TYPE type : CONTACT_TYPE.values()) {
				if (type.code == code) {
					return type.name;
				}
			}
			return null;
		}

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
