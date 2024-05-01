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
@Table(name= "wishlist")
public class Wishlist {
	// 관심 수업 목록
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long wishNo;
	@Column
	private String wishMemid;
	// FK ~ 수업 테이블의 PK 값 (ManyToOne, JoinColumn)
	@Column
	private String wishClno;

}