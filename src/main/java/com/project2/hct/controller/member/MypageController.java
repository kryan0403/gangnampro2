package com.project2.hct.controller.member;

import java.util.HashMap;
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
import com.project2.hct.service.MypageService;

@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	// 마이페이지로 이동
	@GetMapping("MyPage")
	public String mypage(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "member/mypage";
		}else {
			return "redirect:mainP";
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
			return "redirect:member/mypage";
		}
		
		String id = auth.getName();
		System.out.println(id);
		String pwd = mypageService.getMember(id).getMemPw();

		// 입력한 PW 와 암호화된 PW 비교해주는 matches() 메소드로 암호 일치 여부 확인
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isPasswordMatch = passwordEncoder.matches(joinDTO.getMemPw(), pwd);

		if (!isPasswordMatch) {   // 비번 불일치시
			System.out.println("불일치");
			return "redirect:mainP";
		} else {										// 비번 일치시			
			System.out.println("일치");
			mypageService.update(joinDTO);
			return "redirect:member/mypage";
		}
		
	}
	
	// 탈퇴 페이지
	@RequestMapping("deleteMember")
	public String leaveMem(Authentication auth,Model model) {
		System.out.println("탈퇴P");
		
		if (auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "member/deleteMember";
		}else {
			return "redirect:mainP";
		}
	}
	
	// 회원정보 삭제
	@PostMapping("deleteMember.do")
	public String memberDel(@RequestParam("memPw") String memPw, Authentication auth) throws Exception {
		System.out.println("탈퇴");
		
		if ( auth == null) {
			return "redirect:mainP";
		}
		
		String id = auth.getName();
		String pwd = mypageService.getMember(id).getMemPw();
		
		// 입력한 PW 와 암호화된 PW 비교해주는 matches() 메소드로 암호 일치 여부 확인
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isPasswordMatch = passwordEncoder.matches(memPw, pwd);

		if (!isPasswordMatch) {   // 비번 불일치시
			System.out.println("불일치");
			return "member/deleteMember";
		} else {										// 비번 일치시			
			System.out.println("일치");
			mypageService.deleteMember(id);	// delete SQL문 실행
			return "redirect:logout";
		}
	}

}
