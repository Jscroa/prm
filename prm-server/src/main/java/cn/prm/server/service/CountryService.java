package cn.prm.server.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.Constants;
import cn.prm.server.commons.Constants.DB_STATUS;
import cn.prm.server.commons.UUIDUtil;
import cn.prm.server.dao.ICountryDao;
import cn.prm.server.dto.ListDto;
import cn.prm.server.dto.bean.CountryDto;
import cn.prm.server.entity.Country;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.form.CountryForm;

/**
 * @Title: CountryService.java<br>
 * @Package: cn.prm.server.service<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:40:31<br>
 * @version v1.0<br>
 */
@Service(value = "countryService")
public class CountryService {

    private static final Logger log = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    ICountryDao                 countryDao;

    /**
     * @Title: createCountry<br>
     * @Description: <br>
     * @param currUser
     * @param form
     * @throws BusinessException
     */
    public void createCountry(CurrUser currUser, CountryForm form) throws BusinessException {
        String stdName = form.getStdName();
        String enName = form.getEnName();
        String cnName = form.getCnName();
        int code = form.getCode();

        if (stdName == null || "".equals(stdName)) {
            throw new BusinessException("国家缩写不能为空");
        }
        if (enName == null || "".equals(enName)) {
            throw new BusinessException("国家英文名不能为空");
        }
        if (cnName == null || "".equals(cnName)) {
            throw new BusinessException("国家中文名不能为空");
        }
        // if(code == 0){
        // throw new BusinessException("请正确输入国家代码");
        // }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Country country = new Country();
        country.setGuid(UUIDUtil.randomUUID());
        country.setStdName(stdName);
        country.setStdCode(code);
        country.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        country.setCreateTime(now);
        country.setModifyTime(now);
        country.setCreateUser(currUser.getGuid());
        country.setModifyUser(currUser.getGuid());
        country.setEnName(enName);
        country.setCnName(cnName);
        countryDao.add(country);
    }

    /**
     * @Title: getAll<br>
     * @Description: <br>
     * @return
     */
    public ListDto<CountryDto> getAll() {
        List<Country> countries = countryDao.getByStatus(DB_STATUS.STATUS_ACTIVE, "en_name asc");
        ListDto<CountryDto> list = new ListDto<>();
        if (countries == null || countries.size() == 0) {
            return list;
        }
        List<CountryDto> dtos = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
            Country country = countries.get(i);
            CountryDto dto = new CountryDto();
            dto.setId(country.getGuid());
            dto.setEnName(country.getEnName());
            dto.setCnName(country.getCnName());
            dto.setShortName(country.getStdName());
            dtos.add(dto);
        }
        list.setRows(dtos);
        return list;
    }
}
