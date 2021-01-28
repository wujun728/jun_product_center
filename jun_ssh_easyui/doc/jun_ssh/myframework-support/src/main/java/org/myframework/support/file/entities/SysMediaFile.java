package org.myframework.support.file.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.myframework.dao.orm.BaseDomain;
import org.myframework.support.csv.annotation.CsvColumn;
import org.myframework.support.csv.config.CsvTable;

@Entity
@Table(name = "TBL_SYS_MEDIA_FILE")
public class SysMediaFile extends BaseDomain<String>{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "MEDIA_ID" ,length=40)
	@CsvColumn(type=CsvTable.UUID_VALUE)
	private String mediaId;

	@Column(name = "MEDIA_TYPE")
	private String mediaType;

	@CsvColumn(desc="原文件名",type=CsvTable.CUSTOM,format="file.*.FileConverter!doSth2" )
	@Column(name = "OLD_FILE_NAME")
	private String oldFileName;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_URI")
	private String fileUri;

	@Column(name = "FILE_TYPE")
	private String fileType;

	@Column(name = "FILE_EXT")
	private String fileExt;

	@Column(name = "FILE_TITLE")
	private String fileTitle;

	@Column(name = "FILE_SIZE")
	private Long size;

	@Column(name = "CONTENT_TYPE")
	private String contentType;

	@Column(name = "FILE_DESC")
	private String fileDesc;

	@Column(name = "FILE_HTTP_URL")
	private String fileHttpUrl;

	@Column(name = "ENABLED")
	private String state;


	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getOldFileName() {
		return oldFileName;
	}

	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileExt() {
		return fileExt;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getFileHttpUrl() {
		return fileHttpUrl;
	}

	public void setFileHttpUrl(String fileHttpUrl) {
		this.fileHttpUrl = fileHttpUrl;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "SysMediaFile [mediaId=" + mediaId + ", mediaType=" + mediaType
				+ ", oldFileName=" + oldFileName + ", fileName=" + fileName
				+ ", fileUri=" + fileUri + ", fileType=" + fileType
				+ ", fileExt=" + fileExt + ", fileTitle=" + fileTitle
				+ ", size=" + size + ", contentType=" + contentType
				+ ", fileDesc=" + fileDesc + ", fileHttpUrl=" + fileHttpUrl
				+ ", state=" + state + "]";
	}



}
