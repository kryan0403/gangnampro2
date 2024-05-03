package com.project2.hct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.hct.entity.Classnotice;

import jakarta.transaction.Transactional;

@Repository
public interface ClassnoticeRepository extends JpaRepository<Classnotice, Long>{
	public List<Classnotice> findAll();

	public List<Classnotice> findByClnClno(String ClnClno);
	public List<Classnotice> findByClnClnoOrderByClnNoDesc(String clnClno);
	
	@Transactional
	public void deleteByClnClno(String clnClno);
	//public void update();
	//public void save();
	//
}
