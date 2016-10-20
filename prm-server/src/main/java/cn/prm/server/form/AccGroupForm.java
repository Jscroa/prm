package cn.prm.server.form;

import cn.prm.server.commons.BaseForm;
import cn.prm.server.commons.FormLimitAnnotation;
import cn.prm.server.commons.FormLimitAnnotation.CharSupport;

/**
 * @Title: AccGroupForm.java
 * @Package: cn.prm.server.form
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:38:02
 * @version v1.0
 */
public class AccGroupForm extends BaseForm {
	
	@FormLimitAnnotation(minLength=2,maxLength=20,charCheck=CharSupport.AllChar)
	private String groupName;

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
