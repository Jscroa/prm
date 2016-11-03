package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Custom;
import cn.prm.server.entity.GroupToCustom;

/**
 * @Title: IGroupToCustomDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:31:28<br>
 * @version v1.0<br>
 */
public interface IGroupToCustomDao extends IBaseDao<GroupToCustom> {

    /**
     * @Title: getCustoms<br>
     * @Description: <br>
     * @param groupId
     * @param search
     * @param offset
     * @param limit
     * @return
     */
    public List<Custom> getCustoms(String groupId, String search, int offset, int limit);

    /**
     * @Title: getCustomCount<br>
     * @Description: <br>
     * @return
     */
    public int getCustomCount();
    
    /** 
     * @Title: checkCustomOwn<br>
     * @Description: <br>
     * @param accId
     * @param customId
     * @return
     */
    public List<String> checkCustomOwn(String accId,String customId);
}
