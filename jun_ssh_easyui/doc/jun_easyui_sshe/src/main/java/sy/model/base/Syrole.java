package sy.model.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SYROLE", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Syrole implements java.io.Serializable {

	private String id;
	private Date createdatetime;
	private Date updatedatetime;
	private String name;
	private String description;
	private String iconCls;
	private Integer seq;
	private Set<Syuser> syusers = new HashSet<Syuser>(0);
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
	@JoinTable(name = "SYUSER_SYROLE", schema = "", joinColumns = { @JoinColumn(name = "SYROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYUSER_ID", nullable = false, updatable = false) })
	public Set<Syuser> getSyusers() {
		return this.syusers;
	}

	public void setSyusers(Set<Syuser> syusers) {
		this.syusers = syusers;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYROLE_SYRESOURCE", schema = "", joinColumns = { @JoinColumn(name = "SYROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYRESOURCE_ID", nullable = false, updatable = false) })
	public Set<Syresource> getSyresources() {
		return this.syresources;
	}

	public void setSyresources(Set<Syresource> syresources) {
		this.syresources = syresources;
	}

}
