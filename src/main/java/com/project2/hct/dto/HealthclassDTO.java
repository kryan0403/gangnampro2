package com.project2.hct.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "healthclass")
public class HealthclassDTO {
	
	private Long cl_no;
	private String cl_type;
	private String cl_name;
	private String cl_memid;	
	private String cl_memname;
	private String cl_intro;
	private String cl_start;
	private String cl_time;
	private String cl_day;
	private String cl_thumbnail;
	private String cl_profile;
	

}

