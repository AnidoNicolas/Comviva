package com.comviva.exercise2.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESULT_OUTPUT")
public class ResultOutput {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_value")
	private String fileValue;

	@Column(name = "process_date")
	private Date processDate;

	public ResultOutput(String fileName, String fileValue, Date processDate) {
		super();
		this.fileName = fileName;
		this.fileValue = fileValue;
		this.processDate = processDate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileValue() {
		return fileValue;
	}

	public void setFileValue(String fileValue) {
		this.fileValue = fileValue;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
