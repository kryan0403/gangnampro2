package com.project2.hct.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "member")
public class Member {
	
	@Id
	@Column(name="mem_id")
	private String memId;
	@Column(name="mem_pw")
	private String memPw;
	@Column(name="mem_nickname")
	private String memNickname;
	@Column(name="mem_name")
	private String memName;
	@Column(name="mem_birth")
	private String memBirth;
	@Column(name="mem_tel")
	private String memTel;
	@ColumnDefault("'y'")
	@Column(name="mem_active")
	private String memActive;
	
	@CreationTimestamp	// 생성시 당일날짜 자동 입력 - 가입일
	@Column(name="mem_reg")
	private Timestamp memReg;
	
	@UpdateTimestamp	// 업데이트시 당일날짜 자동 입력 - 탈퇴일
	@Column(name="mem_del")
	private Timestamp memDel;

	// Role 로 대체 가능?
	@Column(name="mem_type")
	private String memType;
	
}

