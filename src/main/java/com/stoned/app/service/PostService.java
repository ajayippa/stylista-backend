package com.stoned.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stoned.app.model.Post;

public interface PostService {

	String uploadPost(MultipartFile file,String caption,String userEmail) throws IOException;
	
	List<Post> getAllUserPosts();
}
