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
	@Column(name="live_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int liveNo;
	// FK ~ 수업 테이블의 PK
	@Column(name="cl_no", nullable = false)
	private int clNo;
	// 수업 이름
	@Column(name="live_name", length = 200, nullable = false)
	private String liveName;
	// 썸네일용 사진
	@Column(name="live_photo")
	private String livePhto;
}

