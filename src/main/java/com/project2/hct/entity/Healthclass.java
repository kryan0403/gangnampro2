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
@Table(name= "healthclass")
public class Healthclass {
	@Id
	@Column(name="cl_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clNo;
	// 수업종류 ( ~ : 1, ~ : 2 )
	@Column(name="cl_type")
	private String clType;
	// 수업 이름
	@Column(name="cl_name")
	private String clName;
	// 강사 ID
	@ManyToOne
	@JoinColumn(name = "cl_memId")  // name 속성은 외래키 필드명(내 컬럼명)
	private Member member;  // 참조 테이블(referencedColumnName 생략해서 @ID로 자동 매핑)
	// 강사 이름
	@Column(name="cl_memname")
	private String clMemName;
	// 수업 소개
	@Column(name="cl_intro")
	private String clIntro;
	// 수업 시작 시간
	@Column(name="cl_start")
	private String clStart;
	// 총 시간(분)
	@Column(name="cl_time")
	private String clTime;
	// 수업 진행 요일
	@Column(name="cl_day")
	private String clDay;
	// 썸네일용 사진
	@Column(name="cl_thumbnail")
	private String clThumbnail;
	// 강사 프로필 사진
	@Column(name="cl_profile")
	private String clProfile;

}

