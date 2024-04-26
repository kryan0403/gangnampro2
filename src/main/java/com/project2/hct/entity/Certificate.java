package com.project2.hct.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@ManyToOne  // n(나) : 1(상대) 관계 
	@JoinColumn(name = "cert_memId")  // name 속성은 외래키 필드명(내 컬럼명)
	// referencedColumnName 속성은 참조하는 테이블의 필드명을 쓰는건데 생략하면 ID(PK값) 매핑
	private Member member;  // 참조 테이블(referencedColumnName 생략해서 @ID로 자동 매핑)
	// 자격증 이름
	@Column(name="cert_name")
	private String certName;
	// 자격증 등록일
	@Column(name="cert_reg")
	private String certReg;
	// 자격증 발급기관
	@Column(name="cert_depart")
	private String certDepart;

}

