package cn.prm.server.commons;

import java.util.UUID;

/**
 * @Title: UUIDUtil.java<br>
 * @Package: cn.prm.server.commons<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:28:44<br>
 * @version v1.0<br>
 */
public class UUIDUtil {

    /**
     * @Title: randomUUID<br>
     * @Description: 新随机一个uuid<br>
     * @return
     */
    public static final String randomUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replace("-", "");
        return str;
    }

}
