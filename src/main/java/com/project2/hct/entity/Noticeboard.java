package com.project2.hct.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "noticeboard")
public class Noticeboard {
	@Id
	@Column(name="nb_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nbNo;
	@Column(name="nb_title")
	private String nbTitle;
	@Column(name="nb_content")
	private String nbContent;
	
	@CreationTimestamp
	@Column(name="nb_date")
	private Timestamp nbDate;

}

