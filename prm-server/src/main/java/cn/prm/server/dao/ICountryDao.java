package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Country;

public interface ICountryDao extends IBaseDao<Country> {

	List<Country> getByStatus(int status);
}
