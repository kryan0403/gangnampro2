package com.project2.hct.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "classnotice")
public class Classnotice {
	@Id
	@Column(name="cln_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clnNo;
	@Column(name="cln_content", length = 450, nullable = false)
	private String clnContent;
	// FK ~ 수업 테이블의 PK 값 
	@Column(name="cl_no", nullable = false)
	private int clNo;
	
	@CreationTimestamp
	@Column(name="cln_date")
	private Timestamp clnDate;

}

