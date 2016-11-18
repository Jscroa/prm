/**
 * 
 */
package cn.prm.server.dso;

import java.util.List;

/**
 * @Title: ICustomDso.java<br>
 * @Package: cn.prm.server.dso<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月18日 上午9:59:31<br>
 * @version v1.0<br>
 */
public interface ICustomDso {

    /**
     * @Title: checkCustomOwn<br>
     * @Description: <br>
     * @param accId
     * @param customId
     * @return
     */
    List<String> checkCustomOwn(String accId, String customId);
}
