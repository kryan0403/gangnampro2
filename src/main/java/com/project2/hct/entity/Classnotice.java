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
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clnNo;
	@Column
	private String clnContent;
	// FK ~ 수업 테이블의 PK 값 (ManyToOne, JoinColumn)
	@Column
	private String clnClno;
	
	@CreationTimestamp
	@Column
	private Timestamp clnDate;

}

