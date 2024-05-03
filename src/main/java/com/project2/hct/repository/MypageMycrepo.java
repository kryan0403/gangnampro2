package com.project2.hct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.hct.entity.Myclass;

@Repository
public interface MypageMycrepo extends JpaRepository<Myclass, Long> {

	List<Myclass> findAllBymycMemid(String memid);

}
