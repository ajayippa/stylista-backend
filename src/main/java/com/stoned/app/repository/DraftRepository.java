package com.stoned.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stoned.app.model.Draft;


public interface DraftRepository extends JpaRepository<Draft, Integer>{


	List<Draft> findByUserEmail(String userEmail);
}
