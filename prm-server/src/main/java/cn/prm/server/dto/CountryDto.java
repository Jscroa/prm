package cn.prm.server.dto;

/**
 * @Title: CountryDto.java
 * @Package: cn.prm.server.dto
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:32:09
 * @version v1.0
 */
public class CountryDto {

	private String id;
	private String enName;
	private String cnName;
	private String shortName;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the enName
	 */
	public String getEnName() {
		return enName;
	}
	/**
	 * @param enName the enName to set
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}
	/**
	 * @return the cnName
	 */
	public String getCnName() {
		return cnName;
	}
	/**
	 * @param cnName the cnName to set
	 */
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
