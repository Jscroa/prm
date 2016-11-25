package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Address;
import cn.prm.server.entity.CustomToAddr;

/**
 * @Title: ICustomToAddrDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:31:45<br>
 * @version v1.0<br>
 */
public interface ICustomToAddrDao extends IBaseDao<CustomToAddr> {

    /**
     * @Title: getAddresses<br>
     * @Description: <br>
     * @param customId
     * @return
     */
    List<Address> getAddresses(String customId);
    
    /** 
     * @Title: getbyAddrId<br>
     * @Description: <br>
     * @param addrId
     * @return
     */
    List<CustomToAddr> getbyAddrId(String addrId);

}
