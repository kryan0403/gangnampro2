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
@Table(name= "liveroom")
public class Liveroom {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long liveNo;
	// FK ~ 수업 테이블의 PK 값 (ManyToOne, JoinColumn)
	@Column
	private String liveClno;
	// 수업 이름
	@Column
	private String liveName;
	// 썸네일용 사진
	@Column
	private String liveThumbnail;
	
}

