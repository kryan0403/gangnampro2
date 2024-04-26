package com.project2.hct.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name= "classnotice")
public class Classnotice {
	@Id
	@Column(name="cln_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clnNo;
	@Column(name="cln_content")
	private String clnContent;
	// FK ~ 수업 테이블의 PK 값
	@ManyToOne
	@JoinColumn(name = "cln_clNo")  // name 속성은 외래키 필드명(내 컬럼명)
	private Healthclass healthclass;  // 참조 테이블(referencedColumnName 생략해서 @ID로 자동 매핑)
	
	@CreationTimestamp
	@Column(name="cln_date")
	private Timestamp clnDate;

}

