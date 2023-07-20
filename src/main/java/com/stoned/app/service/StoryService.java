package com.stoned.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stoned.app.exception.StoryAlreadyExistException;
import com.stoned.app.model.Story;



public interface StoryService {
	
	String uploadStory(MultipartFile file,String userEmail) throws IOException, StoryAlreadyExistException;
	
	String uploadTextStory(String caption,String userEmail) throws StoryAlreadyExistException;
	
	Story getStoryByUserEmail(String email);
	
	List<Story> getAllUserStories(String email);
	
	String deleteStoryByUserId(String email);

	public void getStoriesOlderThan24Hours();


}
