/**
 * 
 */
package cn.prm.server.dto.bean;

/**
 * @Title: PassengerDto.java<br>
 * @Package: cn.prm.server.dto.bean<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2017年3月8日 上午11:29:48<br>
 * @version v1.0<br>
 */
public class PassengerDto {

    private String id;
    private String name;
    private String idCard;
    private Boolean sex;
    private String countryId;
    private String countryCn;
    private String countryEn;
    private String countryCode;
    
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the idCard
     */
    public String getIdCard() {
        return idCard;
    }
    /**
     * @param idCard the idCard to set
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    /**
     * @return the sex
     */
    public Boolean getSex() {
        return sex;
    }
    /**
     * @param sex the sex to set
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }
    /**
     * @return the countryId
     */
    public String getCountryId() {
        return countryId;
    }
    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
    /**
     * @return the countryCn
     */
    public String getCountryCn() {
        return countryCn;
    }
    /**
     * @param countryCn the countryCn to set
     */
    public void setCountryCn(String countryCn) {
        this.countryCn = countryCn;
    }
    /**
     * @return the countryEn
     */
    public String getCountryEn() {
        return countryEn;
    }
    /**
     * @param countryEn the countryEn to set
     */
    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }
    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }
    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
}
