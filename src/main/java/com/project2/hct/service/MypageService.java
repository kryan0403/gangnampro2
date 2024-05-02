package com.project2.hct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.hct.entity.Member;
import com.project2.hct.repository.MemberRepository;
import com.project2.hct.repository.MypageMyrepo;
import com.project2.hct.repository.MypageWishrepo;

@Service
public class MypageService {
	
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private MypageMyrepo mypageMyrepo;
	@Autowired
	private MypageWishrepo mypageWishrepo;

	public Member getMember(String id) {
		return memberRepo.findBymemId(id);
	}

}
