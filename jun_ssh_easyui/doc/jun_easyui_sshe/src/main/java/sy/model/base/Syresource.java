package sy.model.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SYRESOURCE", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Syresource implements java.io.Serializable {

	private String pid;// 虚拟属性，用于获得当前资源的父资源ID

	private String id;
	private Date createdatetime;
	private Date updatedatetime;
	private String name;
	private String url;
	private String description;
	private String iconCls;
	private Integer seq;
	private Syresourcetype syresourcetype;
	private Syresource syresource;
	private Set<Syrole> syroles = new HashSet<Syrole>(0);
	private Set<Syorganization> syorganizations = new HashSet<Syorganization>(0);
	private Set<Syresource> syresources = new HashSet<Syresource>(0);

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		if (this.id != null) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYRESOURCETYPE_ID")
	public Syresourcetype getSyresourcetype() {
		return this.syresourcetype;
	}

	public void setSyresourcetype(Syresourcetype syresourcetype) {
		this.syresourcetype = syresourcetype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYRESOURCE_ID")
	public Syresource getSyresource() {
		return this.syresource;
	}

	public void setSyresource(Syresource syresource) {
		this.syresource = syresource;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDATETIME", length = 7)
	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATETIME", length = 7)
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "URL", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ICONCLS", length = 100)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "SEQ", precision = 8, scale = 0)
	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYROLE_SYRESOURCE", schema = "", joinColumns = { @JoinColumn(name = "SYRESOURCE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYROLE_ID", nullable = false, updatable = false) })
	public Set<Syrole> getSyroles() {
		return this.syroles;
	}

	public void setSyroles(Set<Syrole> syroles) {
		this.syroles = syroles;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYORGANIZATION_SYRESOURCE", schema = "", joinColumns = { @JoinColumn(name = "SYRESOURCE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYORGANIZATION_ID", nullable = false, updatable = false) })
	public Set<Syorganization> getSyorganizations() {
		return this.syorganizations;
	}

	public void setSyorganizations(Set<Syorganization> syorganizations) {
		this.syorganizations = syorganizations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "syresource", cascade = CascadeType.ALL)
	public Set<Syresource> getSyresources() {
		return this.syresources;
	}

	public void setSyresources(Set<Syresource> syresources) {
		this.syresources = syresources;
	}

	@Transient
	public String getPid() {
		if (syresource != null && syresource.getId() != null) {
			return syresource.getId();
		}
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
