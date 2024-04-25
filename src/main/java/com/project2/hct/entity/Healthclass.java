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
	@Column(name="cl_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clNo;
	// 수업종류 ( ~ : 1, ~ : 2 )
	@Column(name="cl_type", length = 2, nullable = false)
	private String clType;
	// 수업 이름
	@Column(name="cl_name", length = 200, nullable = false)
	private String clName;
	// 강사 ID
	@Column(name="cl_memid", length = 100, nullable = false)
	private String clMemId;
	// 강사 이름
	@Column(name="cl_memname", length = 50, nullable = false)
	private String clMemName;
	// 수업 소개
	@Column(name="cl_intro", nullable = false)
	private String clIntro;
	// 수업 시작 시간
	@Column(name="cl_start", length = 10, nullable = false)
	private String clStart;
	// 총 시간(분)
	@Column(name="cl_time", length = 10, nullable = false)
	private String clTime;
	// 수업 진행 요일
	@Column(name="cl_day", length = 10, nullable = false)
	private String clDay;
	// 썸네일용 사진
	@Column(name="cl_photo")
	private String clPhto;
	// 강사 프로필 사진
	@Column(name="cl_profile")
	private String clProfile;

}

