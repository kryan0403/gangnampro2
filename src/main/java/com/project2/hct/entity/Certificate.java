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
	@Column(name="cert_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long certNo;
	// 자격증 소지자 id - FK
	@Column(name="cert_id", length = 100, nullable = false)
	private String certId;
	// 자격증 이름
	@Column(name="cert_name", length = 200, nullable = false)
	private String certName;
	// 자격증 등록일
	@Column(name="cert_reg", length = 100, nullable = false)
	private String certReg;
	// 자격증 발급기관
	@Column(name="cert_depart", length = 200, nullable = false)
	private String certDepart;

}

