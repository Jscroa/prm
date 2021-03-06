package cn.prm.server.dto;

/**
 * @Title: PageDto.java<br>
 * @Package: cn.prm.server.dto<br>
 * @Description: 带上分页参数的DTO<br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:33:17<br>
 * @version v1.0<br>
 * @param <T>
 */
public class PageDto<T> extends ListDto<T> {

    private int page;
    private int total;

    /** 
     * Title: <br>
     * Description: <br> 
     */
    public PageDto() {
        super();
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page
     *            the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

}
