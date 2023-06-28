package com.stoned.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stoned.app.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUserEmail(String email);

}
