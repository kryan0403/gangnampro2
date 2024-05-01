package com.project2.hct.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "myclass")
public class Myclass {
	
	// 수강중인 수업목록
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mycNo;
	@Column
	private String mycMemid;
	// FK ~ 수업 테이블의 PK 값 (ManyToOne, JoinColumn)
	@Column
	private String mycClno;

}