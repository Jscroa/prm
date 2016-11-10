package cn.prm.server.dto;

import java.util.List;

/**
 * @Title: ListDto.java<br>
 * @Package: cn.prm.server.dto<br>
 * @Description: 列表形式的DTO<br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:32:57<br>
 * @version v1.0<br>
 * @param <T>
 */
public class ListDto<T> extends BaseDto {

    private List<T> rows;

    /**
     * @return the rows
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * @param rows
     *            the rows to set
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
