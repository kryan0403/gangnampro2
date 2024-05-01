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
@Table(name= "certificate")
public class Certificate {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long certNo;
	// FK ~ 멤버 테이블의 PK 값 -강사ID (ManyToOne, JoinColumn)
	@Column
	private String certMemid;
	@Column
	private String certName;
	@Column
	private String certReg;
	@Column
	private String certDepart;
	
}