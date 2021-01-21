package org.myframework.support.idgenerator.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "TBL_SYS_SEQUENCE")
public class Series implements Serializable {

    /**
     * 唯一标示
     */
	@Id
	@Column(name="SEQUENCE_ID",length=40) 
    private String id;

    /**
     * 名称
     */
	@Column(name="SEQUENCE_NAME") 
    private String name;

    /**
     * 规则，用xml标示
     */
	@Column(name="SEQUENCE_RULE") 
    private String rule;

    /**
     * 备注说明
     */
	@Column(name="MEMO") 
    private String affix;

    /**
     * 当前最大号集
     */
 	@OneToMany(mappedBy="pk.sequenceId" ,targetEntity=CurrentMax.class)
//	@Transient
    private Collection<CurrentMax>  currentMaxs;

    /** full constructor */
    public Series(String id, String name, String rule, String affix, Set currentMaxs) {
        this.id = id;
        this.name = name;
        this.rule = rule;
        this.affix = affix;
        this.currentMaxs = currentMaxs;
    }

    /** default constructor */
    public Series() {
    }

    /** minimal constructor */
    public Series(String id, Collection<CurrentMax> currentMaxs) {
        this.id = id;
        this.currentMaxs = currentMaxs;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRule() {
        return this.rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getAffix() {
        return this.affix;
    }

    public void setAffix(String affix) {
        this.affix = affix;
    }

    public Collection<CurrentMax> getCurrentMaxs() {
        return this.currentMaxs;
    }
//
    public void setCurrentMaxs(Collection<CurrentMax> currentMaxs) {
        this.currentMaxs = currentMaxs;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Series) ) return false;
        Series castOther = (Series) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

}
