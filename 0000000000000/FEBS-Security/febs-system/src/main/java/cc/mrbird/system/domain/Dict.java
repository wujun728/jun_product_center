package cc.mrbird.system.domain;

import cc.mrbird.common.annotation.ExportConfig;
import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_dict")
public class Dict implements Serializable{

	private static final long serialVersionUID = 7780820231535870010L;

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "DICT_ID")
	@ExportConfig(value = "字典ID")
	private Long dictId;

	@Column(name = "KEYY")
	@ExportConfig(value = "字典Key")
	private String keyy;

	@Column(name = "VALUEE")
	@ExportConfig(value = "字典Value")
	private String valuee;

	@Column(name = "TABLE_NAME")
	@ExportConfig(value = "表名")
	private String tableName;

	@Column(name = "FIELD_NAME")
	@ExportConfig(value = "列名")
	private String fieldName;

	/**
	 * @return DICT_ID
	 */
	public Long getDictId() {
		return dictId;
	}

	/**
	 * @param dictId
	 */
	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public String getKeyy() {
		return keyy;
	}

	public void setKeyy(String keyy) {
		this.keyy = keyy;
	}

	public String getValuee() {
		return valuee;
	}

	public void setValuee(String valuee) {
		this.valuee = valuee;
	}

	/**
	 * @return FIELD_NAME
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName == null ? null : fieldName.trim();
	}

	/**
	 * @return TABLE_NAME
	 */
	public String getTableName() {
		return tableName;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("dictId", dictId)
				.add("keyy", keyy)
				.add("valuee", valuee)
				.add("tableName", tableName)
				.add("fieldName", fieldName)
				.toString();
	}

	/**
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName == null ? null : tableName.trim();
	}
}