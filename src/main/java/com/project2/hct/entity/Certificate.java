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

	// 자격증 소지자 id - FK
	@Column(name="cert_id", length = 100, nullable = false)
	private String certId;
	// 자격증 이름
	@Column(name="cert_name", length = 200, nullable = false)
	private String certName;
	@Column
	private String certReg;
	@Column
	private String certDepart;
	
}

