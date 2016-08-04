package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Custom;
import cn.prm.server.entity.GroupToCustom;

public interface IGroupToCustomDao extends IBaseDao<GroupToCustom> {

	public int getCustomCount(String groupId);
	public List<Custom> getCustoms(String groupId,int offset,int limit);
	
}
