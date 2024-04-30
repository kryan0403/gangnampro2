package com.project2.hct.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project2.hct.dto.HealthclassDTO;
import com.project2.hct.dto.PagingPgm;
import com.project2.hct.entity.Healthclass;
import com.project2.hct.service.HealthclassService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HealthclassController {

	@Autowired
	private HealthclassService hs;
	
	String realpath = "C:\\upload";
	
	@RequestMapping("/")
	public String init() {
		return "index.html";
	}
	@RequestMapping("index")
	public String init2() {
		return "index.html";
	}
	@RequestMapping("/healthclass")
	public String lecture() {
		return "redirect:healthclass/All/1";
	}
	
	//리스트 보여주기
	@RequestMapping("/healthclass/{listCategory}/{pageNum}")
	public String select(@PathVariable("listCategory") String listCategory,
			@PathVariable("pageNum") String pageNum,
			Model model) {
		
		final int rowPerPage = 5;
		
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		pageNum += "(";
		int idx = pageNum.indexOf("(");
		pageNum = pageNum.substring(0,idx);
		int currentPage = Integer.parseInt(pageNum);
		
		int total = hs.getTotal(listCategory);
		
		int startRow = (currentPage - 1) * rowPerPage;
		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		if ( listCategory.equals("All"))
			model.addAttribute("selectlist", hs.select(startRow, rowPerPage));
		else {
			model.addAttribute("selectlist", hs.selectCategory(startRow, rowPerPage, listCategory));
		}
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("pp", pp);
		return "healthclass/list.html";
	}
	
	@RequestMapping("healthclass/insertform")
	public String insertform() {
		return "healthclass/insertform.html";
	}
	
	@RequestMapping("healthclass/insert")
	public String insert(@RequestParam("profile") MultipartFile profile,
			@RequestParam("thumbnail") MultipartFile thumbnail,
			HealthclassDTO healthclassDTO, HttpServletRequest hsr) throws IOException {
		
		healthclassDTO.setCl_start(hsr.getParameter("cl_start1")+":"+hsr.getParameter("cl_start2"));
		//insert 후 최신no를 가져온다음(이때는 두개다 null상태), 선택된 파일이 있으면 update해준다
		hs.save(healthclassDTO);
		Integer cl_no = hs.getLastCl_no();
		String newCl_no = cl_no.toString();
		healthclassDTO = hs.getlist(Long.parseLong(newCl_no));
		if ( !profile.getOriginalFilename().equals("") ) {
			hs.updateProfile(newCl_no, profile.getOriginalFilename());
			upload_save(newCl_no, profile);
				
		}
		if ( !thumbnail.getOriginalFilename().equals("") ) {
			hs.updateThumbnail(newCl_no, thumbnail.getOriginalFilename());
			upload_save(newCl_no, thumbnail);
			
		}
		return "healthclass/insertSuccess.html";
	}
	
	@RequestMapping(value="/healthclass/search", method=RequestMethod.GET)
	public String search(Model model, HttpServletRequest hsr) {
		String searchCategory = hsr.getParameter("searchCategory");
		String listCategory = hsr.getParameter("listCategory");
		String searchKeyword = hsr.getParameter("searchKeyword");
		String searchDay = hsr.getParameter("searchDay");
		String pageNum = hsr.getParameter("pageNum");
		final int rowPerPage = 5;
		int currentPage = Integer.parseInt(pageNum);
		
		int total = hs.getTotalBySearch(searchCategory,
				listCategory, searchKeyword, searchDay);
		
		int startRow = (currentPage - 1) * rowPerPage;
		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("searchCategory", searchCategory);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("searchDay", searchDay);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("pp", pp);
		model.addAttribute("selectlist", hs.selectSearch(
											startRow, 
											searchCategory,
											listCategory, 
											searchKeyword, 
											searchDay));
		return "/healthclass/search.html";
	}
	
	@RequestMapping("/healthclass/detail/{cl_no}")
	public String detail(@PathVariable("cl_no") String cl_no, Model model) {
		cl_no += "(";
		int idx = cl_no.indexOf("(");
		cl_no = cl_no.substring(0,idx);
		model.addAttribute("healthclass", hs.getlist(Long.parseLong(cl_no)));
		return "healthclass/detail.html";
	}
	
	@RequestMapping("healthclass/delete/{cl_no}")
	public String delete(@PathVariable("cl_no") String cl_no) throws IOException {
		cl_no += "(";
		int idx = cl_no.indexOf("(");
		cl_no = cl_no.substring(0,idx);
		HealthclassDTO healthclassDTO = hs.getlist(Long.parseLong(cl_no));
		upload_delete(cl_no, healthclassDTO.getCl_profile());
		upload_delete(cl_no, healthclassDTO.getCl_thumbnail());
		hs.delete(cl_no);        
		return "healthclass/deleteSuccess.html";
	}
	
	@RequestMapping("healthclass/updateform/{cl_no}")
	public String updateform(@PathVariable("cl_no") String cl_no,Model model) {
		cl_no += "(";
		int idx = cl_no.indexOf("(");
		cl_no = cl_no.substring(0,idx);
		HealthclassDTO healthclassDTO = hs.getlist(Long.parseLong(cl_no));
		int idx2 = healthclassDTO.getCl_start().indexOf(":");
		model.addAttribute("healthclass", healthclassDTO);
		model.addAttribute("cl_start1", healthclassDTO.getCl_start().substring(0, idx2));
		model.addAttribute("cl_start2", healthclassDTO.getCl_start().substring(idx2+1));
		return "healthclass/updateform.html";
	}
	
	@RequestMapping("healthclass/update/{cl_no}")
	public String update(@PathVariable("cl_no") String cl_no, @RequestParam("profile") MultipartFile profile,
			@RequestParam("thumbnail") MultipartFile thumbnail,
			HealthclassDTO afterDTO,
			HttpServletRequest hsr) throws IOException {
		
		cl_no += "(";
		int idx = cl_no.indexOf("(");
		cl_no = cl_no.substring(0,idx);
		HealthclassDTO beforeDTO = hs.getlist(Long.parseLong(cl_no));
		afterDTO.setCl_no(Long.parseLong(cl_no));
		afterDTO.setCl_start(hsr.getParameter("cl_start1")+":"+hsr.getParameter("cl_start2"));
		
		upload_delete(cl_no, beforeDTO.getCl_profile());
		upload_delete(cl_no, beforeDTO.getCl_thumbnail());
		hs.save(afterDTO);
		
		if ( !profile.getOriginalFilename().equals("") ) {
			upload_save(cl_no, profile);
			hs.updateProfile(cl_no, profile.getOriginalFilename());
		}
		else {
			hs.updateProfileNull(cl_no);
		}
		if (  !thumbnail.getOriginalFilename().equals("")) {
			upload_save(cl_no, thumbnail);
			hs.updateProfile(cl_no, profile.getOriginalFilename());
		}
		else {
			hs.updateThumbnailNull(cl_no);
		}
	
		return "healthclass/updateSuccess.html";
	}
	
	public String upload_save(String cl_no, MultipartFile file) {
		try {
			file.transferTo(new File(cl_no + "_" + file.getOriginalFilename()));
			return "Success";
		}catch(Exception e) { 
			return "Fail";
		}
		
	}
	public String upload_delete(String cl_no, String file_name) {
		File path = new File(realpath+"/");
		try {
			File[] filelist = path.listFiles();
			String[] filelist_names = path.list();
			int idx = Arrays.binarySearch(filelist_names, cl_no +"_" +file_name);
			filelist[idx].delete();
			return "Success";
		}
		catch(Exception e) {
			log.info(e.toString());
			return "Fail";
		}
	}
}	
