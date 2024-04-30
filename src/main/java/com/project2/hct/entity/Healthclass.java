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
@Table(name= "healthclass")
public class Healthclass {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clNo;
	// 수업종류 ( ~ : 1, ~ : 2 )
	@Column
	private String clType;
	// 수업 이름
	@Column
	private String clName;
	// FK ~ 멤버 테이블의 PK 값 -강사ID (ManyToOne, JoinColumn)
	@Column
	private String clMemid;
	// 강사 이름
	@Column
	private String clMemname;
	// 수업 소개
	@Column
	private String clIntro;
	// 수업 시작 시간
	@Column
	private String clStart;
	// 총 시간(분)
	@Column
	private String clTime;
	// 수업 진행 요일
	@Column
	private String clDay;
	// 썸네일용 사진
	@Column
	private String clThumbnail;
	// 강사 프로필 사진
	@Column
	private String clProfile;

}

