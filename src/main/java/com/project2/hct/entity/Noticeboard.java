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
	private int nbNo;
	@Column(name="nb_title", length = 600, nullable = false)
	private String nbTitle;
	@Column(name="nb_content", length = 8000, nullable = false)
	private String nbContent;
	
	@CreationTimestamp
	@Column(name="nb_date")
	private Timestamp nbDate;

}

