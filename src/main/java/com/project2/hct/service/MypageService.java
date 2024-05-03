package com.project2.hct.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.hct.dto.JoinDTO;
import com.project2.hct.entity.Member;
import com.project2.hct.entity.Myclass;
import com.project2.hct.entity.Wishlist;
import com.project2.hct.repository.MemberRepository;
import com.project2.hct.repository.MypageMycrepo;
import com.project2.hct.repository.MypageWishrepo;

@Service
public class MypageService {
	
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private MypageMycrepo mypageMycrepo;
	@Autowired
	private MypageWishrepo mypageWishrepo;

	public Member getMember(String id) {
		return memberRepo.findBymemId(id);
	}

	public void update(JoinDTO joinDTO) {
		// 업데이트 할 멤버 찾기
		Optional<Member> opMem = memberRepo.findById(joinDTO.getMemId());
		
		if(opMem.isPresent()) {
			Member member = opMem.get();
			// 업데이트할 필드만 설정
			member.setMemName(joinDTO.getMemName());
			member.setMemNickname(joinDTO.getMemNickname());
			member.setMemBirth(joinDTO.getMemBirth());
			member.setMemTel(joinDTO.getMemTel());
			//update
			memberRepo.save(member);
		}else {
		}
		
	}

	public void deleteMember(String id) {
		// 업데이트 할 멤버 찾기
		Optional<Member> opMem = memberRepo.findById(id);
		
		if(opMem.isPresent()) {
			Member member = opMem.get();
			// 탈퇴만 설정
			member.setMemActive("n");
			// update
			memberRepo.save(member);
		}else {
		}
	}

	public List<Myclass> getList(String memId) {
		return mypageMycrepo.findAllBymycMemid(memId);
	}

	public void deleteClass(long no) {
		mypageMycrepo.deleteById(no);
	}

	public List<Wishlist> getWishList(String memId) {
		return mypageWishrepo.findAllBywishMemid(memId);
	}

	public void deleteWish(long no) {
		mypageWishrepo.deleteById(no);
	}

}
