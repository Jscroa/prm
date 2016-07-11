package cn.prm.server.commons;

import java.util.UUID;

public class UUIDUtil {

	/**
	 * 新随机一个uuid
	 * 
	 * @return
	 */
	public static final String randomUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString().replace("-", "");
		return str;
	}

}
