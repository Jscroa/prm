/**
 * 
 */
package cn.prm.server.dso;

import java.util.List;

import cn.prm.server.dto.bean.CustomDto;

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
     * @Title: getCustoms<br>
     * @Description: <br>
     * @param groupId
     * @return
     */
    List<CustomDto> getCustoms(String groupId);
    
    /** 
     * @Title: getCustoms<br>
     * @Description: <br>
     * @param groupId
     * @param search
     * @return
     */
    List<CustomDto> getCustoms(String groupId, String search);
    
    /** 
     * @Title: getCustoms<br>
     * @Description: <br>
     * @param groupId
     * @param offset
     * @param limit
     * @return
     */
    List<CustomDto> getCustoms(String groupId, int offset, int limit);
    
    /** 
     * @Title: getCustoms<br>
     * @Description: <br>
     * @param groupId
     * @param search
     * @param offset
     * @param limit
     * @return
     */
    List<CustomDto> getCustoms(String groupId, String search, int offset, int limit);

    /** 
     * @Title: getCustomCount<br>
     * @Description: <br>
     * @return
     */
    int getCustomCount();
    
    /**
     * @Title: checkCustomOwn<br>
     * @Description: <br>
     * @param accId
     * @param customId
     * @return
     */
    List<String> checkCustomOwn(String accId, String customId);
    
    /** 
     * @Title: checkAddressOwn<br>
     * @Description: <br>
     * @param accId
     * @param addressId
     * @return
     */
    List<String> checkAddressOwn(String accId,String addressId);
}
