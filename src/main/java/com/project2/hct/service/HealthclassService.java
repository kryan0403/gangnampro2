package com.project2.hct.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.hct.Repository.HealthclassRepository;
import com.project2.hct.dto.HealthclassDTO;
import com.project2.hct.entity.Healthclass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HealthclassService {
	
	@Autowired
	private HealthclassRepository hr;
	
	public int getLastCl_no() {
		return hr.getLastCl_no();
	}
	public void update(HealthclassDTO healthclassDTO) {
		Healthclass healthclass = new Healthclass();
		healthclass.setClName(healthclassDTO.getCl_name());
		healthclass.setClIntro(healthclassDTO.getCl_type());
		healthclass.setClDay(healthclassDTO.getCl_day());
		healthclass.setClStart(healthclassDTO.getCl_start());
		healthclass.setClTime(healthclassDTO.getCl_time());
		healthclass.setClNo(healthclassDTO.getCl_no());
		
		hr.update(healthclass);
	}

	public void delete(String cl_no) {	
		hr.delete(cl_no);
	}
	public HealthclassDTO getlist(Long cl_no) {
		return hr.findByClNo(cl_no);
	}

	public List<Healthclass> select(int startRow, int endRow) {
		return hr.select(startRow, endRow);
	}
	public List<Healthclass> selectCategory(int startRow, int endRow, String cl_type) {
		if ( cl_type.equals("Yoga") )
			return hr.selectCategory(startRow, endRow, "요가");
		else if( cl_type.equals("Pilates") )
			return hr.selectCategory(startRow, endRow, "필라테스");
		else if( cl_type.equals("Health") )
			return hr.selectCategory(startRow, endRow, "헬스");
		else if( cl_type.equals("Senior") )
			return hr.selectCategory(startRow, endRow, "시니어");
		else if( cl_type.equals("Rehabilitation") )
			return hr.selectCategory(startRow, endRow, "재활");
		else 
			return hr.select(startRow, endRow);
	}
	public int getTotal(String cl_type) {
		if (cl_type.equals("Yoga"))
			return hr.getTotal("요가");
		else if( cl_type.equals("Pilates") )
			return hr.getTotal("필라테스");
		else if( cl_type.equals("Health") )
			return hr.getTotal("헬스");
		else if( cl_type.equals("Senior") )
			return hr.getTotal("시니어");
		else if( cl_type.equals("Rehabilitation") )
			return hr.getTotal("재활");
		else
			return hr.getTotal();
		
	}
	public void insert(HealthclassDTO healthclass) {
		hr.insert(healthclass.getCl_name(),
				healthclass.getCl_intro(),
				healthclass.getCl_type(),
				healthclass.getCl_day(),
				healthclass.getCl_start(),
				healthclass.getCl_time());
		
	}
	public List<Healthclass> selectSearch(int startRow, 
			String searchCategory,
			String cl_type,
			String searchKeyword,
			String searchDay) {
		if (cl_type.equals("Yoga"))
			cl_type = "요가";
		else if ( cl_type.equals("Pilates") )
			cl_type = "필라테스";
		else if( cl_type.equals("Health") )
			cl_type = "헬스";
		else if( cl_type.equals("Senior") )
			cl_type= "시니어";
		else if( cl_type.equals("Rehabilitation") )
			cl_type = "재활";
		else
			cl_type = "전체";
		if ( cl_type.equals("전체")) {
			if ( searchCategory.equals("강좌명") ) {
				return hr.selectSearchAll_Classname(searchKeyword);
			}
			else if ( searchCategory.equals("강사명")) {
				return hr.selectSearchAll_Memname(searchKeyword);
			}
			else return hr.selectSearchAll_Day(searchDay);
		}
		else {
			if ( searchCategory.equals("강좌명") ) {
				return hr.selectSearch_Classname(cl_type, searchKeyword);
			}
			else if ( searchCategory.equals("강사명")) {
				return hr.selectSearch_Memname(cl_type, searchKeyword);
			}
			else return hr.selectSearch_Day(cl_type, searchDay);
		}
	}
	
	public int getTotalBySearch(
			String searchCategory,
			String cl_type, 
			String searchKeyword, 
			String searchDay) {
		if (cl_type.equals("Yoga"))
			cl_type = "요가";
		else if ( cl_type.equals("Pilates") )
			cl_type = "필라테스";
		else if( cl_type.equals("Health") )
			cl_type = "헬스";
		else if( cl_type.equals("Senior") )
			cl_type= "시니어";
		else if( cl_type.equals("Rehabilitation") )
			cl_type = "재활";
		else
			cl_type = "전체";
		if ( cl_type.equals("전체")) {
			if ( searchCategory.equals("강좌명") ) {
				return hr.getTotalBySearchAll_Classname(searchKeyword);
			}
			else if ( searchCategory.equals("강사명")) {
				return hr.getTotalBySearchAll_Memname(searchKeyword);
			}
			else return hr.getTotalBySearchAll_Day(searchDay);
		}
		else {
			if ( searchCategory.equals("강좌명") ) {
				return hr.getTotalBySearch_Classname(cl_type, searchKeyword);
			}
			else if ( searchCategory.equals("강사명")) {
				return hr.getTotalBySearch_Memname(cl_type, searchKeyword);
			}
			else return hr.getTotalBySearch_Day(cl_type, searchDay);
		}
	}
	public void updateProfileNull(String cl_no) {
		hr.updateProfileNull(cl_no);
	}
	public void updateThumbnailNull(String cl_no) {
		hr.updateThumbnailNull(cl_no);
	}
	public void updateProfile(String cl_no, String profile) {
		hr.updateProfile(cl_no, profile);
		
	}
	public void updateThumbnail(String Cl_no, String thumbnail) {
		hr.updateThumbnail(Cl_no, thumbnail);
		
	}
}
