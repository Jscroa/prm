package cn.prm.server.commons;

import java.util.UUID;

/**
 * @Title: UUIDUtil.java
 * @Package: cn.prm.server.commons
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:28:44
 * @version v1.0
 */
public class UUIDUtil {

	/**
	 * 
	 * @Title: randomUUID 
	 * @Description: 新随机一个uuid
	 * @return
	 * @throws
	 */
	public static final String randomUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString().replace("-", "");
		return str;
	}

}
