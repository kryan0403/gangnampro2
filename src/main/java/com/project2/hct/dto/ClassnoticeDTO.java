package com.project2.hct.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ClassnoticeDTO {
	
	private Long clnNo;
	private String clnContent;
	private String clnClno;
	private Timestamp clnDate;
}
