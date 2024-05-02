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
import com.project2.hct.service.ClassnoticeService;
import com.project2.hct.service.HealthclassService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HealthclassController {

	@Autowired
	private HealthclassService hs;
	@Autowired
	private ClassnoticeService cs;
	String realpath = "C:\\Users/user/git/gangnampro2/src/main/resources/static/image";


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
	
	@RequestMapping("healthclass/writeform")
	public String writeform() {
		return "healthclass/writeform.html";
	}
	
	@RequestMapping("healthclass/write")
	public String write(@RequestParam("profile") MultipartFile profile,
			@RequestParam("thumbnail") MultipartFile thumbnail,
			HealthclassDTO healthclassDTO, HttpServletRequest hsr) throws IOException {
		
		healthclassDTO.setClStart(hsr.getParameter("cl_start1")+":"+hsr.getParameter("cl_start2"));
		// 최신no를 가져온다음(이때는 두개다 null상태), 선택된 파일이 있으면 update해준다
		hs.save(healthclassDTO);
		Integer clNo = hs.getLastClNo();
		healthclassDTO = hs.findByClNo(clNo.toString());
		if ( !profile.getOriginalFilename().equals("") ) {
			hs.updateProfile(clNo.toString(), profile.getOriginalFilename());
			upload_save(clNo.toString(), profile);
				
		}
		if ( !thumbnail.getOriginalFilename().equals("") ) {
			hs.updateThumbnail(clNo.toString(), thumbnail.getOriginalFilename());
			upload_save(clNo.toString(), thumbnail);
			
		}
		return "healthclass/writeSuccess.html";
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
	
	@RequestMapping("/healthclass/detail/{clNo}")
	public String detail(@PathVariable("clNo") String clNo, Model model) {
		clNo += "(";
		int idx = clNo.indexOf("(");
		clNo = clNo.substring(0,idx);
		model.addAttribute("healthclass", hs.findByClNo(clNo));
		model.addAttribute("noticelist", cs.findByClnClno(clNo));
		return "healthclass/detail.html";
	}
	
	
	@RequestMapping("healthclass/delete/{clNo}")
	public String delete(@PathVariable("clNo") String clNo) throws IOException {
		clNo += "(";
		int idx = clNo.indexOf("(");
		clNo = clNo.substring(0,idx);
		HealthclassDTO healthclassDTO = hs.findByClNo(clNo);
		upload_delete(clNo, healthclassDTO.getClProfile());
		upload_delete(clNo, healthclassDTO.getClThumbnail());
		hs.delete(clNo);        
		return "healthclass/deleteSuccess.html";
	}
	
	@RequestMapping("healthclass/updateform/{clNo}")
	public String updateform(@PathVariable("clNo") String clNo,Model model) {
		clNo += "(";
		int idx = clNo.indexOf("(");
		clNo = clNo.substring(0,idx);
		HealthclassDTO healthclassDTO = hs.findByClNo(clNo);
		int idx2 = healthclassDTO.getClStart().indexOf(":");
		model.addAttribute("healthclass", healthclassDTO);
		model.addAttribute("cl_start1", healthclassDTO.getClStart().substring(0, idx2));
		model.addAttribute("cl_start2", healthclassDTO.getClStart().substring(idx2+1));
		return "healthclass/updateform.html";
	}
	
	@RequestMapping("healthclass/update/{clNo}")
	public String update(@PathVariable("clNo") String clNo, @RequestParam("profile") MultipartFile profile,
			@RequestParam("thumbnail") MultipartFile thumbnail,
			HealthclassDTO afterDTO,
			HttpServletRequest hsr) throws IOException {
		
		clNo += "(";
		int idx = clNo.indexOf("(");
		clNo = clNo.substring(0,idx);
		HealthclassDTO beforeDTO = hs.findByClNo(clNo);
		afterDTO.setClNo(Long.parseLong(clNo));
		afterDTO.setClStart(hsr.getParameter("cl_start1")+":"+hsr.getParameter("cl_start2"));
		
		upload_delete(clNo, beforeDTO.getClProfile());
		upload_delete(clNo, beforeDTO.getClThumbnail());
		hs.save(afterDTO);
		
		if ( !profile.getOriginalFilename().equals("") ) {
			upload_save(clNo, profile);
			hs.updateProfile(clNo, profile.getOriginalFilename());
		}
		else {
			hs.updateProfileNull(clNo);
		}
		if (  !thumbnail.getOriginalFilename().equals("")) {
			upload_save(clNo, thumbnail);
			hs.updateProfile(clNo, profile.getOriginalFilename());
		}
		else {
			hs.updateThumbnailNull(clNo);
		}
	
		return "healthclass/updateSuccess.html";
	}
	
	public String upload_save(String clNo, MultipartFile file) {
		try {
			file.transferTo(new File(clNo + "_" + file.getOriginalFilename()));
			return "Success";
		}catch(Exception e) { 
			return "Fail";
		}
		
	}
	public String upload_delete(String clNo, String file_name) {
		File path = new File(realpath+"/");
		try {
			File[] filelist = path.listFiles();
			String[] filelist_names = path.list();
			int idx = Arrays.binarySearch(filelist_names, clNo +"_" +file_name);
			filelist[idx].delete();
			return "Success";
		}
		catch(Exception e) {
			log.info(e.toString());
			return "Fail";
		}
	}
}	
