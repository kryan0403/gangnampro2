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
	@Column(name="mem_id", length = 100)
	private String memId;
	@Column(name="mem_pw", length = 60, nullable = false)
	private String memPw;
	@Column(name="mem_nick", length = 100, nullable = false, unique=true)
	private String memNick;
	@Column(name="mem_name", length = 50, nullable = false)
	private String memName;
	@Column(name="mem_birth", length = 6, nullable = false)
	private String memBirth;
	@Column(name="mem_tel", length = 15, nullable = false)
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
	@Column(name="mem_type", length = 1)
	private String memType;
	
}

