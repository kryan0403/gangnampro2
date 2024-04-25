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
	@Column(name="myc_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mycNo;
	@Column(name="myc_memid", length = 100, nullable = false)
	private String mycMemId;
	// FK ~ 수업 테이블의 PK
	@Column(name="cl_no", nullable = false)
	private int clNo;

}

