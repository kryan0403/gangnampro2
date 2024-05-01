package com.project2.hct.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "member")
public class Member {
	
	@Id
	@Column
	private String memId;
	@Column	
	private String memPw;
	@Column
	private String memNickname;
	@Column
	private String memName;
	@Column
	private String memBirth;
	@Column
	private String memTel;
	@Column
	private String memRole;
	@ColumnDefault("'y'")
	@Column
	private String memActive;
	
	@CreationTimestamp  // 생성시 당일날짜 자동 입력 - 가입일
	@Column
	private Timestamp memReg;
	
	@UpdateTimestamp  // 업데이트시 당일날짜 자동 입력 - 탈퇴일
	@Column
	private Timestamp memDel;
}