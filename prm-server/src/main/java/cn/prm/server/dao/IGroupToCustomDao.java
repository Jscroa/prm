package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Custom;
import cn.prm.server.entity.GroupToCustom;

/**
 * @Title: IGroupToCustomDao.java
 * @Package: cn.prm.server.dao
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:31:28
 * @version v1.0
 */
public interface IGroupToCustomDao extends IBaseDao<GroupToCustom> {

	/** 
	 * @Title: getCustoms 
	 * @Description: 
	 * @param groupId
	 * @param search
	 * @param offset
	 * @param limit
	 * @return
	 * @throws 
	 */
	public List<Custom> getCustoms(String groupId,String search,int offset,int limit);
	
	/** 
	 * @Title: getCustomCount 
	 * @Description: 
	 * @return
	 * @throws 
	 */
	public int getCustomCount();
}
