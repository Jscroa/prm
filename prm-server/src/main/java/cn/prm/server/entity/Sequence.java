/**
 * 
 */
package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: Sequence.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月15日 下午4:44:45<br>
 * @version v1.0<br>
 */
public class Sequence extends BaseEntity implements Serializable {
    /**
     * @field serialVersionUID :
     */
    private static final long serialVersionUID = 794115177431871677L;

    private String            seqKey;
    private Integer           seqValue;
    private Integer           minValue;
    private Integer           maxValue;
    private Integer           increment;

    /**
     * @return the seqKey
     */
    public String getSeqKey() {
        return seqKey;
    }

    /**
     * @param seqKey
     *            the seqKey to set
     */
    public void setSeqKey(String seqKey) {
        this.seqKey = seqKey;
    }

    /**
     * @return the seqValue
     */
    public Integer getSeqValue() {
        return seqValue;
    }

    /**
     * @param seqValue
     *            the seqValue to set
     */
    public void setSeqValue(Integer seqValue) {
        this.seqValue = seqValue;
    }

    /**
     * @return the minValue
     */
    public Integer getMinValue() {
        return minValue;
    }

    /**
     * @param minValue
     *            the minValue to set
     */
    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    /**
     * @return the maxValue
     */
    public Integer getMaxValue() {
        return maxValue;
    }

    /**
     * @param maxValue
     *            the maxValue to set
     */
    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * @return the increment
     */
    public Integer getIncrement() {
        return increment;
    }

    /**
     * @param increment
     *            the increment to set
     */
    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

}
