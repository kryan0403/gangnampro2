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
	@Column(name="wish_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wishNo;
	@Column(name="wish_memid", length = 100, nullable = false)
	private String wishMemId;
	// FK ~ 수업 테이블의 PK
	@Column(name="cl_no", nullable = false)
	private int clNo;

}

