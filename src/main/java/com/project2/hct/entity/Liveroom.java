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
@Table(name= "liveroom")
public class Liveroom {
	@Id
	@Column(name="live_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long liveNo;
	// FK ~ 수업 테이블의 PK
	@ManyToOne
	@JoinColumn(name = "live_clNo")  // name 속성은 외래키 필드명(내 컬럼명)
	private Healthclass healthclass;  // 참조 테이블(referencedColumnName 생략해서 @ID로 자동 매핑)
	// 수업 이름
	@Column(name="live_name")
	private String liveName;
	// 썸네일용 사진
	@Column(name="live_thumbnail")
	private String liveThumbnail;
}

