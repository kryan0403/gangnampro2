package com.project2.hct.controller.member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project2.hct.dto.JoinDTO;
import com.project2.hct.entity.Member;
import com.project2.hct.entity.Myclass;
import com.project2.hct.entity.Wishlist;
import com.project2.hct.service.MypageService;

@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	// 마이페이지로 이동
	@GetMapping("mypageL")
	public String mypage(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/mypage";
		}else {
			return "redirect:/main";
		}
		
	}
	
	// 수강목록 보기
	@GetMapping("classListP")
	public String classListP(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/classListPage";
		}else {
			return "redirect:/main";
		}
	}
	
	// 관심목록 보기
	@GetMapping("wishListP")
	public String wishListP(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/wishListPage";
		}else {
			return "redirect:/main";
		}
	}
	
	// 내글목록 보기
	@GetMapping("writeListP")
	public String writeListP(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/writeListPage";
		}else {
			return "redirect:/main";
		}
	}
	
	// 내강의목록 보기
	@GetMapping("myClassP")
	public String myClassP(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/myClassPage";
		}else {
			return "redirect:/main";
		}
	}
	
	// 수정폼 불러오기
	@GetMapping("updateMemForm/{memId}")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> updateform(@PathVariable("memId") String memId){
		
		System.out.println("수정폼 id : "+memId);
		Member editm = mypageService.getMember(memId);
		
		Map map = new HashMap<>();
		map.put("editm", editm);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 회원정보 수정
	@PostMapping("member_edit.do")
	public String member_edit_ok(JoinDTO joinDTO,Authentication auth) throws Exception {
		
		System.out.println("수정");
		
		if ( auth == null) {
			return "redirect:mypage";
		}
		
		String id = auth.getName();
		System.out.println(id);
		String pwd = mypageService.getMember(id).getMemPw();

		// 입력한 PW 와 암호화된 PW 비교해주는 matches() 메소드로 암호 일치 여부 확인
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isPasswordMatch = passwordEncoder.matches(joinDTO.getMemPw(), pwd);

		if (!isPasswordMatch) {   // 비번 불일치시
			System.out.println("불일치");
			return "redirect:/main";
		} else {										// 비번 일치시			
			System.out.println("일치");
			mypageService.update(joinDTO);
			return "redirect:mypage";
		}
		
	}
	
	// 탈퇴 페이지
	@RequestMapping("deleteMember")
	public String leaveMem(Authentication auth,Model model) {
		System.out.println("탈퇴P");
		
		if (auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/deleteMember";
		}else {
			return "redirect:main";
		}
	}
	
	// 회원정보 삭제
	@PostMapping("deleteMember.do")
	public String memberDel(@RequestParam("memPw") String memPw, Authentication auth) throws Exception {
		System.out.println("탈퇴");
		
		if ( auth == null) {
			return "redirect:/main";
		}
		
		String id = auth.getName();
		String pwd = mypageService.getMember(id).getMemPw();
		
		// 입력한 PW 와 암호화된 PW 비교해주는 matches() 메소드로 암호 일치 여부 확인
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isPasswordMatch = passwordEncoder.matches(memPw, pwd);

		if (!isPasswordMatch) {   // 비번 불일치시
			System.out.println("불일치");
			return "mypage/deleteMember";
		} else {										// 비번 일치시			
			System.out.println("일치");
			mypageService.deleteMember(id);	// delete SQL문 실행
			return "redirect:/logout";
		}
	}
	
	// 수강 목록
	@GetMapping("getClassList/{memId}")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> getClassList(@PathVariable("memId") String memId){
		
		List<Myclass> list = mypageService.getList(memId);
		
		Map map = new HashMap<>();
		map.put("list", list);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 수강 취소
	@GetMapping("deleteClass/{no}")
	@ResponseBody
	public void deleteClass(@PathVariable("no") long no) {
		mypageService.deleteClass(no);
	}
	
	// 관심 목록
	@GetMapping("getWishList/{memId}")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> getWishList(@PathVariable("memId") String memId){
		
		List<Wishlist> list = mypageService.getWishList(memId);
		
		Map map = new HashMap<>();
		map.put("list", list);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 관심 삭제
	@GetMapping("deleteWish/{no}")
	@ResponseBody
	public void deleteWish(@PathVariable("no") long no) {
		mypageService.deleteWish(no);
	}

}
