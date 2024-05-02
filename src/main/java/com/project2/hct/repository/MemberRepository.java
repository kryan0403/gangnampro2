package com.project2.hct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.hct.entity.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member, String>{

	//회원 아이디 중복 확인
	boolean existsBymemId(String memId);
	
	//memId를 가지고 회원정보 조회 (로그인), 내정보/비번확인(수정,탈퇴)
	Member findBymemId(String memId);
}
