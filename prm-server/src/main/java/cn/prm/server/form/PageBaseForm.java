package cn.prm.server.form;

import cn.prm.server.commons.BaseForm;
import cn.prm.server.commons.FormLimitAnnotation;
import cn.prm.server.commons.FormLimitAnnotation.CharSupport;

/**
 * @Title: PageBaseForm.java
 * @Package: cn.prm.server.form
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:38:50
 * @version v1.0
 */
public class PageBaseForm extends BaseForm {

	@FormLimitAnnotation(minLength=0,maxLength=100,charCheck=CharSupport.AllChar)
	private String search;
	@FormLimitAnnotation(minLength=0,maxLength=100,charCheck=CharSupport.AllChar)
	private String order;
	private int offset;
	private int limit;
	
	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}
	/**
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}
	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
