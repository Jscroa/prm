package cn.prm.server.commons;

/**
 * 常量
 * @author yyao
 *
 */
public class Constants {

	/**
	 * session key
	 * @author yyao
	 *
	 */
	public static class SESSION{
		public static final String LOGIN_USER = "cn.prm.server.commons.Constants.SESSION.LOGIN_USER";
	}
	
	/**
	 * response code
	 * @author yyao
	 *
	 */
	public static class RESPONSE_CODE{
		public static final int CODE_SUCCESS = 100;
		public static final int CODE_FAILURE = 200;
		
	}
	
	/**
	 * db status
	 * @author yyao
	 *
	 */
	public static class DB_STATUS{
		public static final int STATUS_ACTIVE = 100;
		public static final int STATUS_INACTIVE = 200;
	}
	
	public static class MAV_KEYS{
		public static final String USER_NAME = "userName";
	}
	
}
