/**
 * @author th
 * @version 1.0
 * @since 1.0
 */

package com.acec.wgt.models.ser;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.bestnet.base.vo.BaseVO;

public class SMSWhitelist extends BaseVO {

    // date formats

    // columns START
    private Integer id;
    private String mobile;
    private Integer schoolId;

    // columns END

    public SMSWhitelist() {
    }

    public SMSWhitelist(Integer id) {
	this.id = id;
    }

    public void setId(Integer value) {
	this.id = value;
    }

    public Integer getId() {
	return this.id;
    }

    public void setMobile(String value) {
	this.mobile = value;
    }

    public String getMobile() {
	return this.mobile;
    }

    public void setSchoolId(Integer value) {
	this.schoolId = value;
    }

    public Integer getSchoolId() {
	return this.schoolId;
    }

    public String toString() {
	return new ToStringBuilder(this).append("Id", getId()).append("Mobile", getMobile())
		.append("SchoolId", getSchoolId()).toString();
    }

    public int hashCode() {
	return new HashCodeBuilder().append(getId()).append(getMobile()).append(getSchoolId()).toHashCode();
    }

    public boolean equals(Object obj) {
	if (obj instanceof SMSWhitelist == false)
	    return false;
	if (this == obj)
	    return true;
	SMSWhitelist other = (SMSWhitelist) obj;
	return new EqualsBuilder().append(getId(), other.getId()).append(getMobile(), other.getMobile())
		.append(getSchoolId(), other.getSchoolId()).isEquals();
    }
}
