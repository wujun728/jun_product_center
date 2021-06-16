package sy.model.demo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
@Entity
@Table(name = "tc")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DemoC implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_id")
	private DemoB demob;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DemoB getDemob() {
		return demob;
	}

	public void setDemob(DemoB demob) {
		this.demob = demob;
	}

}
