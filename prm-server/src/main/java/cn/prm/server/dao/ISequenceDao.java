/**
 * 
 */
package cn.prm.server.dao;

import cn.prm.server.entity.Sequence;

/**
 * @Title: ISequenceDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月15日 下午4:47:35<br>
 * @version v1.0<br>
 */
public interface ISequenceDao extends IBaseDao<Sequence>{
    
    /** 
     * @Title: getByKey<br>
     * @Description: <br>
     * @param seqKey
     * @return
     */
    Sequence getByKey(String seqKey);

}
