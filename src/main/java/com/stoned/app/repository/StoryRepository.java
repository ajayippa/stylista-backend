package com.stoned.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.stoned.app.model.Story;

import jakarta.transaction.Transactional;

public interface StoryRepository extends JpaRepository<Story, Integer>{

	@Query("SELECT s FROM Story s WHERE s.userEmail <> ?1")
	List<Story> findByUserEmailNotEquals(String email);
	
	Story findByUserEmail(String email);


	List<Story> findByTimestamp(String date);

	@Modifying
	@Transactional
	@Query("delete from Story s1 where s1.timestamp <=:date")
	void deleteByTimestamp(String date);

	
}
