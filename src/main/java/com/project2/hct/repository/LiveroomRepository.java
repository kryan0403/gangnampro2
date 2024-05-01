package com.project2.hct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project2.hct.entity.Liveroom;

@Repository
public interface LiveroomRepository extends JpaRepository<Liveroom, Long>{
	
	@Query(value="select * from liveroom", nativeQuery = true)
	public List<Liveroom> findroom();
}